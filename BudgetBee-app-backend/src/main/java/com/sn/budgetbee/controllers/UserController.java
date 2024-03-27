package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.UserAlreadyExistsException;
import com.sn.budgetbee.exception.UserNotFoundException;
import com.sn.budgetbee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // infirizzo per accedere alle api
@CrossOrigin(origins = "http://localhost:3000")
public class UserController{

    //Implementazione della dipendenza con la service
    private final UserService SERVICE;

    @Autowired
    public UserController(UserService SERVICE) {
        this.SERVICE = SERVICE;
    }

    // Metodo che richiama il metodo presente nella service per restituite la lista completa della tabella
    @CrossOrigin
    @GetMapping("/users") // l'indirizzo per accedere alla lista (es: host/api/users)
    public List<UserDTO> getAllUser() {
        return SERVICE.findAllUsers();
    }

    // Metodo che richiama il metodo presente nella service per restituite l'elemento corrispondente all'id
    @CrossOrigin
    @GetMapping("/users/{userId}") // l'indirizzo per accedere alla lista dove UserId corrisponde all'id nel parametro id (es: host/api/users/2)
    public UserDTO getUserById(@PathVariable("userId") Integer id) {
        return SERVICE.findUserById(id);
    }

    // Metodo che richiama il metodo presente nella service per salvare un nuovo elemento nella tabella
    // Da sistamre con il tipo generico -----------------
    @CrossOrigin
    @PostMapping("/users")
    public UserDTO setUser(@RequestBody User user) {
        user.setId(0); // si imposta l'id a 0 per far capire al db che si tratta di un utente non esistente
        return SERVICE.saveUser(user);
    }

    // Metodo che richiama il metodo presente nella service per aggiornare un elemento nella tabella
    // Da sistamre con il tipo generico -----------------------
    @CrossOrigin
    @PutMapping("/users")
    public UserDTO updatUser(@RequestBody User user) {
        return SERVICE.saveUser(user);
    }

    // Metodo che richiama il metodo presente nella service per eliminare un elemento nella tabella
    @CrossOrigin
    @DeleteMapping("/users/{userId}")
    public boolean deleteUserById(@PathVariable("userId") Integer id) {
        return SERVICE.deleteUserById(id);
    }

    @CrossOrigin
    @GetMapping("/users/login")
    public @ResponseBody UserDTO login(@RequestParam String username,
                                       @RequestParam String password) {
        return SERVICE.checkLogin(username, password);
    }

    @CrossOrigin
    @GetMapping("/users/checkusers")
    public @ResponseBody boolean checkUsername(@RequestParam String username) {
        return SERVICE.UsernameIsPresent(username);
    }

    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(UserNotFoundException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(UserAlreadyExistsException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
