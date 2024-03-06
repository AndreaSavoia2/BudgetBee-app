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
    public List<Exit> getAllElements() {

        return SERVICE.findAllExits();
    }

    @Override
    @GetMapping("/exits/{exitId}")
    public Exit getElementById(@PathVariable("exitId")Integer id) {
        return SERVICE.findExitById(id);
    }

    @GetMapping("/exits/{budgetId}")
    public List<Exit> getElemenByBudgetId(@PathVariable("budgetId")Integer id) {return SERVICE.exitListByIdBudget(id); }

    @Override
    @PostMapping("/exits")
    public Exit setElement(@RequestBody Exit exit) {
        exit.setId(0);
        return SERVICE.saveExit(exit);
    }

    @Override
    @PutMapping("/exits")
    public Exit updateElement(@RequestBody Exit exit) {
        return SERVICE.saveExit(exit);
    }

    @Override
    @DeleteMapping("/exits/{exitId}")
    public boolean deleteElementById(@PathVariable("exitId") Integer id) {
        return SERVICE.deleteExitById(id);
    }
}
