package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    UserDTO findUserById(Integer id);

    List<UserDTO> findAllUsers();

    boolean deleteUserById(Integer id);

    Integer checkLogin(String username, String password);

    boolean UsernameIsPresent(String username);

}
