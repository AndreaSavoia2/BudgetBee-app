package com.sn.budgetbee.repos;

import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExitDAO extends JpaRepository<Exit, Integer> {

    @Query("SELECT x FROM Exit x JOIN x.budget b WHERE b.id = :budgetId")
    List<Exit> findExitsByBudgetId(@Param("budgetId") Integer budgetId);

    @Query("SELECT NEW com.sn.budgetbee.dto.FilterExitDTO(x.category, SUM(x.transaction)) " +
            "FROM Exit x " +
            "RIGHT JOIN x.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(x.transactionDate, '%Y/%m') = :month GROUP BY x.category")
    List<FilterExitDTO> findTotalExitByCategoryAndMonth(@Param("budgetId") Integer budgetId, @Param("month") String month);

    @Query("SELECT NEW com.sn.budgetbee.dto.FilterExitDTO(x.category, SUM(x.transaction)) " +
            "FROM Exit x " +
            "RIGHT JOIN x.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(x.transactionDate, '%Y') = :year GROUP BY x.category")
    List<FilterExitDTO> findTotalExitByCategoryAndYear(@Param("budgetId") Integer budgetId, @Param("year") String year);

    @Query("SELECT SUM(x.transaction) " +
            "FROM Exit x " +
            "RIGHT JOIN x.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(x.transactionDate, '%Y/%m') = :month GROUP BY x.category")
    Integer findTotalExitByMonth(@Param("budgetId") Integer budgetId, @Param("month") String month);

    @Query("SELECT SUM(x.transaction) " +
            "FROM Exit x " +
            "RIGHT JOIN x.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(x.transactionDate, '%Y') = :year GROUP BY x.category")
    Integer findTotalExitByYear(@Param("budgetId") Integer budgetId, @Param("month") String year);

}
