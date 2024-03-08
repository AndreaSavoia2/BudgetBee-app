package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.exception.BudgetNotFoundExceprion;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetController implements ControllerInterface<Budget> {

    private final BudgetService SERVICE;

    public BudgetController(BudgetService service) {
        this.SERVICE = service;
    }

    @Override
    @GetMapping("/budgets")
    public List<Budget> getAllElements() {
        return SERVICE.findAllBudgets();
    }

    @Override
    @GetMapping("/budgets/{budgetId}")
    public Budget getElementById(@PathVariable("budgetId") Integer id) {
        return SERVICE.findBudgetById(id);
    }

    @Override
    @PostMapping("/budgets")
    public Budget setElement(@RequestBody Budget budget) {
        budget.setId(0);
        return SERVICE.saveBudget(budget);
    }

    @Override
    @PutMapping("/budgets")
    public Budget updateElement(@RequestBody Budget budget) {
        return SERVICE.saveBudget(budget);
    }

    @Override
    @DeleteMapping("/budgets/{budgetId}")
    public boolean deleteElementById(@PathVariable("budgetId") Integer id) {
        return SERVICE.deleteBudgetById(id);
    }

    @PutMapping("/budgets/calculate")
    public @ResponseBody Budget calcolateBudget(@RequestParam Integer id, @RequestParam Double movement){
        return SERVICE.movementOnBudget(id,movement);
    }

    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(BudgetNotFoundExceprion exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
