package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.repos.BudgetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService{

    private final BudgetDAO BUDGET_DAO;

    @Autowired
    public BudgetServiceImpl(BudgetDAO budgetDAO) {
        this.BUDGET_DAO = budgetDAO;
    }

    @Override
    public Budget saveBudget(Budget budget) {
        return BUDGET_DAO.save(budget);
    }

    @Override
    public Budget findBudgetById(Integer id) {
        Optional<Budget> result = BUDGET_DAO.findById(id);
        Budget budget = null;

        if(result.isPresent()){
            budget = result.get();
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: " + id);
        }

        return budget;
    }

    @Override
    public List<Budget> findAllBudgets() {
        return BUDGET_DAO.findAll();
    }

    @Override
    public boolean deleteBudgetById(Integer id) {
        Optional<Budget> result = BUDGET_DAO.findById(id);

        if(result.isPresent()){
            BUDGET_DAO.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: :" + id);
        }
    }

    @Override
    public Budget movementOnBudget(Integer id,Double movement) {
        Budget budget = this.findBudgetById(id);
        double operation = budget.getBudget();
        operation += movement;
        budget.setBudget(operation);
        return this.saveBudget(budget);
    }

}
