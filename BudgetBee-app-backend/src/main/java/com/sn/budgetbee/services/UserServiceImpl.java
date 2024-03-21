package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.UserAlreadyExistsException;
import com.sn.budgetbee.exception.UserNotFoundException;
import com.sn.budgetbee.repos.BudgetDAO;
import com.sn.budgetbee.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO USER_DAO;
    private final PasswordEncoder PASSWORD_ENCODER; //necessario alla crypto non copiare nelle altre Implementazioni
    private final BudgetDAO BUDGET_DAO;

    // Iniettore delle dipendenze
    @Autowired
    public UserServiceImpl(UserDAO userDAO, BudgetDAO budgetDAO){
        this.USER_DAO = userDAO;
        this.BUDGET_DAO = budgetDAO;
        this.PASSWORD_ENCODER = new BCryptPasswordEncoder(); //necessario alla crypto non copiare nelle altre Implementazioni
    }

    // Metodo per salvare o modificare un utente già esistente
    @Override
    public UserDTO saveUser(User user) {

        User userConvertion;

        if(user.getId() == 0){

            if(!this.UsernameIsPresent(user.getUsername())){
                String encodePassword = this.PASSWORD_ENCODER.encode(user.getPassword()); //necessario alla crypto non copiare nelle altre Implementazioni
                user.setPassword(encodePassword); //necessario alla crypto non copiare nelle altre Implementazioni
                if (user.getBudget() == null){
                    user.setBudget(new Budget(0.00));
                }

                userConvertion= USER_DAO.save(user);
                UserDTO userDTO = new UserDTO(
                        userConvertion.getId(),
                        userConvertion.getUsername(),
                        userConvertion.getBudget());
                return userDTO;
            }else{
                throw new UserAlreadyExistsException("USER IS A UNIQUE FIELD IN THE DATABASE, THE VALUE ENTERED IS ALREADY PRESENT: " + user.getUsername());
            }

        }else{

            String encodePassword = this.PASSWORD_ENCODER.encode(user.getPassword()); //necessario alla crypto non copiare nelle altre Implementazioni
            user.setPassword(encodePassword); //necessario alla crypto non copiare nelle altre Implementazioni
            return null;

        }

    }

    // Metodo che restituisce un elemento in base al suo id
    @Override
    public UserDTO findUserById(Integer id) {
        Optional<User> resultUser = USER_DAO.findById(id);
        User user = null;
        UserDTO userDTO = null;

        if(resultUser.isPresent()){
            user = resultUser.get();
            userDTO = new UserDTO(user.getId(),user.getUsername(), BUDGET_DAO.findBudgetsByUserId(user.getId()));
        }else{
            throw new UserNotFoundException("NO ID USER FOUND: " + id);
        }

        return userDTO;
    }

    // Metodo che restituisce la lista completa degli elementi della tabella
    @Override
    public List<UserDTO> findAllUsers() {

        List<User> users = USER_DAO.findAll();
        List<UserDTO> usersDto = new ArrayList<>();


        for (User user: users){

            usersDto.add(new UserDTO(user.getId(),user.getUsername(),BUDGET_DAO.findBudgetsByUserId(user.getId())));
        }

        return usersDto;
    }

    // Metodo che elimina un entità dalla tabella in base al suo id
    @Override
    public boolean deleteUserById(Integer id) {
        Optional<User> result = USER_DAO.findById(id);

        if(result.isPresent()){
            USER_DAO.deleteById(id);
            return true;
        }else{
            throw new UserNotFoundException("NO ID USER FOUND: " + id);
        }
    }

    // Metodi unici per la User Service non sono da copiare nelle altre implementazioni --------------------------------
    @Override
    public UserDTO checkLogin(String username, String password) {
        List<User> users = USER_DAO.findAll();
        for (User user: users) {
            if(user.getUsername().equals(username) && PASSWORD_ENCODER.matches(password, user.getPassword())){
                return new UserDTO(user.getId(),user.getUsername(), BUDGET_DAO.findBudgetsByUserId(user.getId()));
            }
        }
        return null;
    }

    @Override
    public boolean UsernameIsPresent(String username) {
        User user = USER_DAO.findUserByName(username);
        return user != null;
    }

}
