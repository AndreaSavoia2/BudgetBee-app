package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.repos.BudgetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BudgetServiceImpl implements BudgetService{

    private BudgetDAO budgetDAO;

    @Autowired
    public BudgetServiceImpl(BudgetDAO budgetDAO) {
        this.budgetDAO = budgetDAO;
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetDAO.save(budget);
    }

    @Override
    public Budget findBudgetById(Integer id) {
        return null;
    }

    @Override
    public List<Budget> findAllBudgets() {
        return null;
    }

    @Override
    public boolean deleteBudgetById(Integer id) {
        return false;
    }


}
