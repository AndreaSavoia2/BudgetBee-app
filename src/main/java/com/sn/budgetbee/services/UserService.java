package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserById(Integer id);

    List<User> findUsersAll();

    boolean deleteById(Integer id);
}
