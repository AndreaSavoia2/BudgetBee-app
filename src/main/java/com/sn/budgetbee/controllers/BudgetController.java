package com.sn.budgetbee.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BudgetController implements ControllerInterface<BudgetController>{
    @Override
    public List<BudgetController> GetAllElements() {
        return null;
    }

    @Override
    public BudgetController GetElementById(Integer id) {
        return null;
    }

    @Override
    public BudgetController SetElement(BudgetController budgetController) {
        return null;
    }

    @Override
    public BudgetController UpdateElement(BudgetController budgetController) {
        return null;
    }

    @Override
    public boolean DeleteElementById(Integer id) {
        return false;
    }
}
