package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.EntranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EntranceController implements  ControllerInterface<Entrance> , ControlletDtoInterface<EntranceDTO> {

    private final EntranceService SERVICE;

    public EntranceController(EntranceService service) {
        this.SERVICE = service;
    }

    @Override
    @CrossOrigin
    @GetMapping("/entrances")
    public List<EntranceDTO> getAllElementsDto() {
        return SERVICE.findAllEntrance();
    }

    @Override
    @CrossOrigin
    @GetMapping("/entrances/{entranceId}")
    public EntranceDTO getElementDtoById(@PathVariable("entranceId") Integer id) {
        return SERVICE.findEntranceById(id);
    }

    @CrossOrigin
    @GetMapping("/entrances/budgetid/{budgetId}")
    public List<EntranceDTO> getElementByBudgetId(@PathVariable("budgetId") Integer id) { return SERVICE.entranceListByIdBudget(id); }

    @CrossOrigin
    @GetMapping("/entrances/filter/month")
    public @ResponseBody List<FilterEntranceDTO> getElementByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.entranceListByCategoryAndMonth(id, month);
    }

    @CrossOrigin
    @GetMapping("/entrances/filter/year")
    public @ResponseBody List<FilterEntranceDTO> getElementByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.entranceListByCategoryAndYear(id, year);
    }

    @CrossOrigin
    @GetMapping("/entrances/total/month")
    public @ResponseBody Double getElementByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.entraceByMonth(id, month); }

    @CrossOrigin
    @GetMapping("/entrances/total/year")
    public @ResponseBody Double getElementByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.entraceByYear(id, year); }

    @Override
    @CrossOrigin
    @PostMapping("/entrances")
    public Entrance setElement(@RequestBody Entrance entrance) {
        entrance.setId(0);
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @CrossOrigin
    @PutMapping("/entrances")
    public Entrance updateElement(@RequestBody Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @Override
    @CrossOrigin
    @DeleteMapping("/entrances/{entranceId}")
    public boolean deleteElementById(@PathVariable("entranceId") Integer id) {
        return SERVICE.deleteEntranceById(id);
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
