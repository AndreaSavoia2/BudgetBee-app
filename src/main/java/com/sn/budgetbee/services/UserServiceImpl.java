package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.repos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO dao;

    @Autowired
    public UserServiceImpl(UserDAO dao){
        this.dao = dao;
    }

    @Override
    public User saveUser(User user) {
        return dao.save(user);
    }

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public List<User> findUsersAll() {
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
