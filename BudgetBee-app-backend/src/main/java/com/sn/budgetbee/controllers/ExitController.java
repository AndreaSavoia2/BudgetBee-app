package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.ExitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExitController{

    private final ExitService SERVICE;

    public ExitController(ExitService service) {
        this.SERVICE = service;
    }

    @CrossOrigin
    @GetMapping("/exits")
    public List<ExitDTO> getAllExits() {
        return SERVICE.findAllExits();
    }

    @CrossOrigin
    @GetMapping("/exits/{exitId}")
    public ExitDTO getExitById(@PathVariable("exitId") Integer id) {
        return SERVICE.findExitById(id);
    }

    @CrossOrigin
    @GetMapping("/exits/budgetid/{budgetId}")
    public List<ExitDTO> getExitByBudgetId(@PathVariable("budgetId")Integer id) {return SERVICE.exitListByIdBudget(id); }

    @CrossOrigin
    @GetMapping("/exits/filter/month")
    public @ResponseBody List<FilterExitDTO> getExitsByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.exitListByCategoryAndMonth(id, month);
    }

    @CrossOrigin
    @GetMapping("/exits/filter/year")
    public @ResponseBody List<FilterExitDTO> getExitsByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.exitListByCategoryAndYear(id, year);
    }

    @CrossOrigin
    @GetMapping("/exits/total/month")
    public @ResponseBody  Double getExitsByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.exitByMonth(id, month); }

    @CrossOrigin
    @GetMapping("/exits/total/year")
    public @ResponseBody  Double getExitsByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.exitByYear(id, year); }

    @CrossOrigin
    @PostMapping("/exits")
    public Exit setExit(@RequestBody Exit exit) {
        exit.setId(0);
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @PutMapping("/exits")
    public Exit updateExit(@RequestBody Exit exit) {
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @DeleteMapping("/exits/{exitId}")
    public boolean deleteExitById(@PathVariable("exitId") Integer id) {
        return SERVICE.deleteExitById(id);
    }

    @CrossOrigin
    @GetMapping("/exits/filter/mouthlist")
    public @ResponseBody List<FilterExitListTotalDTO> getExitsTotalMouthListByYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.exitListTotalMonthByYear(id, year);
    }

    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(ExitNotFoundException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
