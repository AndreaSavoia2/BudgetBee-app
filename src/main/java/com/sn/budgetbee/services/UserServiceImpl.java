package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(User user) {
        String encodePassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userDAO.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> result = userDAO.findById(id);
        User user = null;

        if(result.isPresent()){
            user = result.get();
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: " + id);
        }

        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public boolean deleteUserById(Integer id) {
        Optional<User> result = userDAO.findById(id);

        if(result.isPresent()){
            userDAO.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: :" + id);
        }
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<User> users = userDAO.findAll();
        for (User user: users) {
            if(user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean UsernameIsPresent(String username) {
        User user = userDAO.findUserByName(username);
        return user == null;
    }

}
