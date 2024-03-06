package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.entities.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntranceDAO extends JpaRepository<Entrance, Integer> {

    @Query("SELECT e FROM Entrance e JOIN e.budget b WHERE b.id = :budgetId")
    List<Entrance> findEntranceByBudgetId(@Param("budgetId") Integer budgetId);

    @Query("SELECT SUM(e.transaction) as total, e.category " +
            "FROM Entrance e " +
            "RIGHT JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(e.transactionDate, '%Y/%m') = :month GROUP BY e.category")
    List<Entrance> findTotalEntranceByCategoryAndMonth(@Param("budgetId") Integer budgetId, @Param("month") String month);

    @Query("SELECT SUM(e.transaction) as total, e.category " +
            "FROM Entrance e " +
            "RIGHT JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(e.transactionDate, '%Y') = :year GROUP BY e.category")
    List<Entrance> findTotalEntranceByCategoryAndYear(@Param("budgetId") Integer budgetId, @Param("year") String year);
}
