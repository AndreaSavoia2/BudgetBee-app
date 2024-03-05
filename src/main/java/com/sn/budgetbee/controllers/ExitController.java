package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Exit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExitController implements ControllerInterface<Exit>{
    @Override
    public List GetAllElements() {
        return null;
    }

    @Override
    public Exit GetElementById(Integer id) {
        return null;
    }

    @Override
    public Exit SetElement(Exit exit) {
        return null;
    }

    @Override
    public Exit UpdateElement(Exit exit) {
        return null;
    }

    @Override
    public boolean DeleteElementById(Integer id) {
        return false;
    }
}
