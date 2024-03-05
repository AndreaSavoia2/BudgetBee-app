package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.services.ExitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExitController implements ControllerInterface<Exit>{

    private final ExitService SERVICE;

    public ExitController(ExitService service) {
        this.SERVICE = service;
    }

    @Override
    @GetMapping("/exits")
    public List<Exit> GetAllElements() {

        return SERVICE.findAllExits();
    }

    @Override
    @GetMapping("/exits{exitId}")
    public Exit GetElementById(@PathVariable("exitId")Integer id) {
        return SERVICE.findExitById(id);
    }

    @Override
    @PostMapping("/exits")
    public Exit SetElement(@RequestBody Exit exit) {
        exit.setId(0);
        return SERVICE.saveExit(exit);
    }

    @Override
    @PutMapping("/exits")
    public Exit UpdateElement(@RequestBody Exit exit) {
        return SERVICE.saveExit(exit);
    }

    @Override
    @DeleteMapping("/exits/{exitId}")
    public boolean DeleteElementById(@PathVariable("exitId") Integer id) {
        return SERVICE.deleteExitById(id);
    }
}
