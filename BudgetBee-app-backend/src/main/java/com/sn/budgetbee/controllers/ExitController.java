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
public class ExitController implements ControllerInterface<Exit> , ControlletDtoInterface<ExitDTO>{

    private final ExitService SERVICE;

    public ExitController(ExitService service) {
        this.SERVICE = service;
    }

    @Override
    @CrossOrigin
    @GetMapping("/exits")
    public List<ExitDTO> getAllElementsDto() {
        return SERVICE.findAllExits();
    }

    @Override
    @CrossOrigin
    @GetMapping("/exits/{exitId}")
    public ExitDTO getElementDtoById(@PathVariable("exitId") Integer id) {
        return SERVICE.findExitById(id);
    }

    @CrossOrigin
    @GetMapping("/exits/budgetid/{budgetId}")
    public List<ExitDTO> getElementByBudgetId(@PathVariable("budgetId")Integer id) {return SERVICE.exitListByIdBudget(id); }

    @CrossOrigin
    @GetMapping("/exits/filter/month")
    public @ResponseBody List<FilterExitDTO> getElementByBudgetIdAndMonth(@RequestParam Integer id, @RequestParam String month) {
        return SERVICE.exitListByCategoryAndMonth(id, month);
    }

    @CrossOrigin
    @GetMapping("/exits/filter/year")
    public @ResponseBody List<FilterExitDTO> getElementByBudgetIdAndYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.exitListByCategoryAndYear(id, year);
    }

    @CrossOrigin
    @GetMapping("/exits/total/month")
    public @ResponseBody  Double getElementByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.exitByMonth(id, month); }

    @CrossOrigin
    @GetMapping("/exits/total/year")
    public @ResponseBody  Double getElementByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.exitByYear(id, year); }

    @CrossOrigin
    @Override
    @PostMapping("/exits")
    public Exit setElement(@RequestBody Exit exit) {
        exit.setId(0);
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @Override
    @PutMapping("/exits")
    public Exit updateElement(@RequestBody Exit exit) {
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @Override
    @DeleteMapping("/exits/{exitId}")
    public boolean deleteElementById(@PathVariable("exitId") Integer id) {
        return SERVICE.deleteExitById(id);
    }

    @CrossOrigin
    @GetMapping("/exits/filter/mouthlist")
    public @ResponseBody List<FilterExitListTotalDTO> getElementTotalMouthListByYear(@RequestParam Integer id, @RequestParam String year) {
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
