package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.TransactionDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.entities.Transaction;
import com.sn.budgetbee.exception.BudgetNotFoundExceprion;
import com.sn.budgetbee.repos.BudgetDAO;
import com.sn.budgetbee.repos.EntranceIconDAO;
import com.sn.budgetbee.repos.ExitIconDAO;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService{

    private final BudgetDAO BUDGET_DAO;
    private final ExitIconDAO EXIT_ICON_DAO;
    private final EntranceIconDAO ENTRANCE_ICON_DAO;

    @Autowired
    public BudgetServiceImpl(BudgetDAO BUDGET_DAO, ExitIconDAO EXIT_ICON_DAO, EntranceIconDAO ENTRANCE_ICON_DAO) {
        this.BUDGET_DAO = BUDGET_DAO;
        this.EXIT_ICON_DAO = EXIT_ICON_DAO;
        this.ENTRANCE_ICON_DAO = ENTRANCE_ICON_DAO;
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
            throw new BudgetNotFoundExceprion("NO ID BUDGET FOUND: " + id);
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
            throw new BudgetNotFoundExceprion("NO ID BUDGET FOUND: " + id);
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

    @Override
    public List<TransactionDTO> findAllByBudgetId(Integer id) {

        List<Object[]> transactions = BUDGET_DAO.findExitsAndEntrancesByBudgetId(id);
        List<TransactionDTO> transactionsDTO = new ArrayList<>();


        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        for (Object[] objects : transactions) {
            Exit exit = (Exit) objects[0];
            Entrance entrance = (Entrance) objects[1];

            if (exit != null) {
                String link = EXIT_ICON_DAO.findLink(exit.getCategory());
                transactionsDTO.add(new ExitDTO(
                        exit.getId(),
                        exit.getTransaction(),
                        exit.getDescription(),
                        exit.getTransactionDate().format(formatterDate),
                        exit.getTransactionDate().format(formatterHor),
                        exit.getCategory(),
                        link));
            }

            if (entrance != null) {
                String link = ENTRANCE_ICON_DAO.findLink(entrance.getCategory());
                transactionsDTO.add(new EntranceDTO(
                        entrance.getId(),
                        entrance.getTransaction(),
                        entrance.getDescription(),
                        entrance.getTransactionDate().format(formatterDate),
                        entrance.getTransactionDate().format(formatterHor),
                        entrance.getCategory(),
                        link));
            }


        }

        return transactionsDTO;
    }

}
