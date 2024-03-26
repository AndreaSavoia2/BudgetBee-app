package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.EntranceService;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EntranceController{

    private final EntranceService SERVICE;

    public EntranceController(EntranceService service) {
        this.SERVICE = service;
    }

    @CrossOrigin
    @GetMapping("/entrances")
    public List<EntranceDTO> getAllEntrancesDto() {
        return SERVICE.findAllEntrance();
    }

    @CrossOrigin
    @GetMapping("/entrances/{entranceId}")
    public EntranceDTO getEntranceById(@PathVariable("entranceId") Integer id) {
        return SERVICE.findEntranceById(id);
    }

    @CrossOrigin
    @GetMapping("/entrances/budgetid/{budgetId}")
    public List<EntranceDTO> getEntrancesByBudgetId(@PathVariable("budgetId") Integer id) { return SERVICE.entranceListByIdBudget(id); }

    @CrossOrigin
    @GetMapping("/entrances/filter/month")
    public @ResponseBody List<FilterEntranceDTO> getEntrancesByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.entranceListByCategoryAndMonth(id, month);
    }

    @CrossOrigin
    @GetMapping("/entrances/filter/year")
    public @ResponseBody List<FilterEntranceDTO> getEntrancesByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.entranceListByCategoryAndYear(id, year);
    }

    @CrossOrigin
    @GetMapping("/entrances/total/month")
    public @ResponseBody Double getEntrancesByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.entraceByMonth(id, month); }

    @CrossOrigin
    @GetMapping("/entrances/total/year")
    public @ResponseBody Double getEntrancesByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.entraceByYear(id, year); }

    @CrossOrigin
    @PostMapping("/entrances")
    public Entrance setEntrance(@RequestBody Entrance entrance) {
        entrance.setId(0);
        return SERVICE.saveEntrance(entrance);
    }

    @CrossOrigin
    @PutMapping("/entrances")
    public Entrance updateEntrance(@RequestBody Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @CrossOrigin
    @DeleteMapping("/entrances/{entranceId}")
    public boolean deleteEntranceById(@PathVariable("entranceId") Integer id) {
        return SERVICE.deleteEntranceById(id);
    }

    @CrossOrigin
    @GetMapping("/entrances/filter/category")
    public @ResponseBody List<EntranceDTO> getAllentranceByCategory(@RequestParam Integer id, @RequestParam EntranceCategories category){
        return SERVICE.entranceListByIdBudgetAndCategory(id,category);
    }

    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(EntranceNotFoundException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
