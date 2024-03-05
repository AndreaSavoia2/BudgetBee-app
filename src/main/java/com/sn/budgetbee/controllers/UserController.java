package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController implements ControllerInterface<User> {

    private final UserService SERVICE;

    @Autowired
    public UserController(UserService SERVICE) {
        this.SERVICE = SERVICE;
    }

    @Override
    @GetMapping("/users")
    public List<User> GetAllElements() {
        return SERVICE.findAllUsers();
    }

    @Override
    @GetMapping("/users/{userId}")
    public User GetElementById(@PathVariable("userId") Integer id) {
        return SERVICE.findUserById(id);
    }

    @Override
    @PostMapping("/users")
    public User SetElement(@RequestBody User user) {
        user.setId(0);
        return SERVICE.saveUser(user);
    }

    @Override
    @PutMapping("/users")
    public User UpdateElement(User user) {
        return SERVICE.saveUser(user);
    }

    @Override
    @DeleteMapping("/users/{userId}")
    public boolean DeleteElementById(Integer id) {
        return SERVICE.deleteUserById(id);
    }
}
