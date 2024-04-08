package com.sn.budgetbee.repos;

import com.sn.budgetbee.dto.TransactionDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BudgetDAO extends JpaRepository<Budget, Integer> {

    @Query("SELECT b FROM Budget b JOIN b.user u WHERE u.id = :userId")
    Budget findBudgetsByUserId(@Param("userId") Integer userId);

    @Query("SELECT e AS exit, en AS entrance FROM Budget b " +
            "LEFT JOIN Exit e ON e.budget.id = b.id " +
            "LEFT JOIN Entrance en ON en.budget.id = b.id " +
            "WHERE b.id = :budgetId " +
            "ORDER BY COALESCE(e.transactionDate, en.transactionDate) DESC")
    List<Object[]> findExitsAndEntrancesByBudgetId(@Param("budgetId") Integer budgetId);
}
