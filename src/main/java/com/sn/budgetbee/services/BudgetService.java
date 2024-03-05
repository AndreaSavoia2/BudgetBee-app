package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Budget;

import java.util.List;

public interface BudgetService {

    Budget saveBudget(Budget budget);

    Budget findBudgetById(Integer id);

    List<Budget> findAllBudgets();

    boolean deleteBudgetById(Integer id);

    Budget movementOnBudget(Integer id,Double movement);
}
