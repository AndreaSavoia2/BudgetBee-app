package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.FilterEntranceDTO;
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
    public List<Entrance> getAllElements() {
        return SERVICE.findEntrancesAll();
    }

    @Override
    @GetMapping("/entrances/{entranceId}")
    public Entrance getElementById(@PathVariable("entranceId") Integer id) {
        return SERVICE.findEntranceById(id);
    }

    @GetMapping("/entrances/{budgetId}")
    public List<Entrance> getElementByBudgetId(@PathVariable("budgetId") Integer id) { return SERVICE.entranceListByIdBudget(id); }

    @GetMapping("/entrances/filter/month")
    public @ResponseBody List<FilterEntranceDTO> getElementByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.entranceListByCategoryAndMonth(id, month);
    }

    @GetMapping("/entrances/filter/year")
    public @ResponseBody List<FilterEntranceDTO> getElementByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.entranceListByCategoryAndYear(id, year);
    }

    @GetMapping("/entrances/total/month")
    public @ResponseBody Integer getElementByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.entraceByMonth(id, month); }

    @GetMapping("/entrances/total/year")
    public @ResponseBody Integer getElementByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.entraceByYear(id, year); }

    @Override
    @PostMapping("/entrances")
    public Entrance setElement(@RequestBody Entrance entrance) {
        entrance.setId(0);
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @PutMapping("/entrances")
    public Entrance updateElement(@RequestBody Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @DeleteMapping("/entrances/{entranceId}")
    public boolean deleteElementById(@PathVariable("entranceId") Integer id) {
        return SERVICE.deleteEntranceById(id);
    }
}
