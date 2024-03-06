package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // infirizzo per accedere alle api
public class UserController implements ControllerInterface<User>/* L'interfaccia ControllerInterface è un interfaccia con tipi generici condivisa tra le classi controller */{

    //Implementazione della dipendenza con la service
    private final UserService SERVICE;

    @Autowired
    public UserController(UserService SERVICE) {
        this.SERVICE = SERVICE;
    }

    // Metodo che richiama il metodo presente nella service per restituite la lista completa della tabella
    @Override
    @GetMapping("/users") // l'indirizzo per accedere alla lista (es: host/api/users)
    public List<User> getAllElements() {
        return SERVICE.findAllUsers();
    }

    @GetMapping("/users2") // l'indirizzo per accedere alla lista (es: host/api/users)
    public List<UserDTO> getAllElements2() {
        return SERVICE.findAllUsers2();
    }

    // Metodo che richiama il metodo presente nella service per restituite l'elemento corrispondente all'id
    @Override
    @GetMapping("/users/{userId}") // l'indirizzo per accedere alla lista dove UserId corrisponde all'id nel parametro id (es: host/api/users/2)
    public User getElementById(@PathVariable("userId") Integer id) {
        return SERVICE.findUserById(id);
    }

    // Metodo che richiama il metodo presente nella service per salvare un nuovo elemento nella tabella
    @Override
    @PostMapping("/users")
    public User setElement(@RequestBody User user) {
        user.setId(0); // si imposta l'id a 0 per far capire al db che si tratta di un utente non esistente
        return SERVICE.saveUser(user);
    }

    // Metodo che richiama il metodo presente nella service per aggiornare un elemento nella tabella
    @Override
    @PutMapping("/users")
    public User updateElement(@RequestBody User user) {
        return SERVICE.saveUser(user);
    }

    // Metodo che richiama il metodo presente nella service per eliminare un elemento nella tabella
    @Override
    @DeleteMapping("/users/{userId}")
    public boolean deleteElementById(@PathVariable("userId") Integer id) {
        return SERVICE.deleteUserById(id);
    }

    @GetMapping("/users/login")
    public @ResponseBody Integer login(@RequestParam String username, @RequestParam String password) {
        return SERVICE.checkLogin(username, password);
    }

    @GetMapping("/users/checkusers")
    public @ResponseBody boolean checkUsername(@RequestParam String username) {
        return SERVICE.UsernameIsPresent(username);
    }
}
