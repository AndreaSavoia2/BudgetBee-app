package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetDAO extends JpaRepository<Budget, Integer> {
}
