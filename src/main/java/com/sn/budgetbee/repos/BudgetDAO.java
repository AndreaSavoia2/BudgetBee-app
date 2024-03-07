package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BudgetDAO extends JpaRepository<Budget, Integer> {

    @Query("SELECT b FROM Budget b JOIN b.user u WHERE u.id = :userId")
    Budget findBudgetsByUserId(@Param("userId") Integer userId);
}
