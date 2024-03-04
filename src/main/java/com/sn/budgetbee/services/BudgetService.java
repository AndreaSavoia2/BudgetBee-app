package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Budget;

import java.util.List;

public interface BudgetService {

    Budget save(Budget budget);

    Budget findBudgetById(Integer id);

    List<Budget> findBudgetsAll();

    boolean deleteById(Integer id);
}
