package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Entrance;
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
    public List<Exit> getElementByBudgetId(@PathVariable("budgetId")Integer id) {return SERVICE.exitListByIdBudget(id); }

    @GetMapping("/exits/filter/month")
    public @ResponseBody List<FilterExitDTO> getElementByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.exitListByCategoryAndMonth(id, month);
    }

    @GetMapping("/exits/filter/year")
    public @ResponseBody List<FilterExitDTO> getElementByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.exitListByCategoryAndYear(id, year);
    }

    @GetMapping("/exits/total/month")
    public @ResponseBody Integer getElementByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.exitByMonth(id, month); }

    @GetMapping("/exits/total/year")
    public @ResponseBody Integer getElementByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.exitByYear(id, year); }

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
