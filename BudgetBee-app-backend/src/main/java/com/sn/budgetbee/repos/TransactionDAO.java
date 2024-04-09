package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionDAO extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t  WHERE t.budgetId = :budgetId ORDER BY t.transactionDate DESC")
    List<Transaction> findTransactionsByBudgetId(@Param("budgetId") Integer budgetId, Pageable pageable);

}
