package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.services.EntranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EntranceController implements  ControllerInterface<Entrance>{

    private final EntranceService SERVICE;

    public EntranceController(EntranceService service) {
        this.SERVICE = service;
    }

    @Override
    @GetMapping("/entrances")
    public List<Entrance> GetAllElements() {
        return SERVICE.findEntrancesAll();
    }

    @Override
    @GetMapping("/entrances/{entranceId}")
    public Entrance GetElementById(@PathVariable("entranceId") Integer id) {
        return SERVICE.findEntranceById(id);
    }

    @Override
    @PostMapping("/entrances")
    public Entrance SetElement(@RequestBody Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @PutMapping("/entrances")
    public Entrance UpdateElement(Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @DeleteMapping("/entrances/{entranceId}")
    public boolean DeleteElementById(@RequestParam Integer id) {
        return SERVICE.deleteEntranceById(id);
    }
}
