package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Entrance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EntranceController implements  ControllerInterface<Entrance>{
    @Override
    public List<Entrance> GetAllElements() {
        return null;
    }

    @Override
    public Entrance GetElementById(Integer id) {
        return null;
    }

    @Override
    public Entrance SetElement(Entrance entrance) {
        return null;
    }

    @Override
    public Entrance UpdateElement(Entrance entrance) {
        return null;
    }

    @Override
    public boolean DeleteElementById(Integer id) {
        return false;
    }
}
