package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserById(Integer id);

    UserDTO findUserById2(Integer id);

    List<User> findAllUsers();

    List<UserDTO> findAllUsers2();

    boolean deleteUserById(Integer id);

    Integer checkLogin(String username, String password);

    boolean UsernameIsPresent(String username);

}
