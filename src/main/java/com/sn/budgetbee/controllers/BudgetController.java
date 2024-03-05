package com.sn.budgetbee.controllers;

import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.services.BudgetService;
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
    public List<Budget> GetAllElements() {
        return SERVICE.findAllBudgets();
    }

    @Override
    @GetMapping("/budgets/{budgetId}")
    public Budget GetElementById(@PathVariable("budgetId") Integer id) {
        return SERVICE.findBudgetById(id);
    }

    @Override
    @PostMapping("/budgets")
    public Budget SetElement(@RequestBody Budget budget) {
        budget.setId(0);
        return SERVICE.saveBudget(budget);
    }

    @Override
    @PutMapping("/budgets")
    public Budget UpdateElement(@RequestBody Budget budget) {
        return SERVICE.saveBudget(budget);
    }

    @Override
    @DeleteMapping("/budgets/{budgetId}")
    public boolean DeleteElementById(@PathVariable("budgetId") Integer id) {
        return SERVICE.deleteBudgetById(id);
    }
}
