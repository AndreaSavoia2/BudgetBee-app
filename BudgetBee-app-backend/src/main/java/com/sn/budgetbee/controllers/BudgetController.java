package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.TransactionDTO;
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
public class BudgetController{

    private final BudgetService SERVICE;

    public BudgetController(BudgetService service) {
        this.SERVICE = service;
    }

    @CrossOrigin
    @GetMapping("/budgets")
    public List<Budget> getAllBudgets() {
        return SERVICE.findAllBudgets();
    }

    @CrossOrigin
    @GetMapping("/budgets/{budgetId}")
    public Budget getBudgetById(@PathVariable("budgetId") Integer id) {
        return SERVICE.findBudgetById(id);
    }

    @CrossOrigin
    @GetMapping("/budgets/transaction/{budgetId}")
    public List<TransactionDTO> getTransactionByBudgetId(@PathVariable("budgetId") Integer id) {
        return SERVICE.findAllByBudgetId(id);
    }

    @CrossOrigin
    @PostMapping("/budgets")
    public Budget setBudget(@RequestBody Budget budget) {
        budget.setId(0);
        return SERVICE.saveBudget(budget);
    }

    @CrossOrigin
    @PutMapping("/budgets")
    public Budget updateBudget(@RequestBody Budget budget) {
        return SERVICE.saveBudget(budget);
    }

    @CrossOrigin
    @DeleteMapping("/budgets/{budgetId}")
    public boolean deleteBudgetById(@PathVariable("budgetId") Integer id) {
        return SERVICE.deleteBudgetById(id);
    }

    @CrossOrigin
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
