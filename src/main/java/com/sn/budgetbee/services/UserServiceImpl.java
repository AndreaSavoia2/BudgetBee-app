package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO USER_DAO;
    private final PasswordEncoder PASSWORD_ENCODER; //necessario alla crypto non copiare nelle altre Implementazioni

    // Iniettore delle dipendenze
    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.USER_DAO = userDAO;
        this.PASSWORD_ENCODER = new BCryptPasswordEncoder(); //necessario alla crypto non copiare nelle altre Implementazioni
    }

    // Metodo per salvare o modificare un utente già esistente
    @Override
    public User saveUser(User user) {

        if(!this.UsernameIsPresent(user.getUsername())){
            String encodePassword = this.PASSWORD_ENCODER.encode(user.getPassword()); //necessario alla crypto non copiare nelle altre Implementazioni
            user.setPassword(encodePassword); //necessario alla crypto non copiare nelle altre Implementazioni
            return USER_DAO.save(user);
        }else{
            throw new RuntimeException("ERROR USERNAME GIA PRESENTE NEL DATABASE");
        }

    }

    // Metodo che restituisce un elemento in base al suo id
    @Override
    public User findUserById(Integer id) {
        Optional<User> result = USER_DAO.findById(id);
        User user = null;

        if(result.isPresent()){
            user = result.get();
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: " + id);
        }

        return user;
    }

    // Metodo che restituisce la lista completa degli elementi della tabella
    @Override
    public List<User> findAllUsers() {
        return USER_DAO.findAll();
    }

    @Override
    public List<UserDTO> findAllUsers2() {

        List<User> users = USER_DAO.findAll();
        List<UserDTO> usersDto = new ArrayList<>();

        for (User user: users){
            usersDto.add(new UserDTO(user.getId(),user.getUsername()));
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
            throw new RuntimeException("NO ID USER FOUND ERROR: :" + id);
        }
    }

    // Metodi unici per la User Service non sono da copiare nelle altre implementazioni --------------------------------
    @Override
    public Integer checkLogin(String username, String password) {
        List<User> users = USER_DAO.findAll();
        for (User user: users) {
            if(user.getUsername().equals(username) && PASSWORD_ENCODER.matches(password, user.getPassword())){
                return user.getId();
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
