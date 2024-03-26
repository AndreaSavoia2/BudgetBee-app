package com.sn.budgetbee.repos;

import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExitDAO extends JpaRepository<Exit, Integer> {

    @Query("SELECT x FROM Exit x JOIN x.budget b WHERE b.id = :budgetId ORDER BY FUNCTION('DATE_FORMAT', x.transactionDate, '%d/%m/%Y') DESC")
    List<Exit> findExitsByBudgetId(@Param("budgetId") Integer budgetId);

    @Query("SELECT x FROM Exit x JOIN x.budget b WHERE b.id = :budgetId AND x.category = :category ORDER BY FUNCTION('DATE_FORMAT', x.transactionDate, '%d/%m/%Y') DESC")
    List<Exit> findExitsByBudgetIdAndCategory(@Param("budgetId") Integer budgetId, @Param("category") ExitCategories category);

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
            "AND DATE_FORMAT(x.transactionDate, '%Y/%m') = :month ")
    Double findTotalExitByMonth(@Param("budgetId") Integer budgetId, @Param("month") String month);

    @Query("SELECT SUM(x.transaction) " +
            "FROM Exit x " +
            "RIGHT JOIN x.budget b " +
            "WHERE b.id = :budgetId " +
            "AND DATE_FORMAT(x.transactionDate, '%Y') = :year ")
    Double findTotalExitByYear(@Param("budgetId") Integer budgetId, @Param("year") String year);

    @Query("SELECT FUNCTION('DATE_FORMAT', e.transactionDate, '%m/%Y') AS month, " +
            "SUM(e.transaction) AS total " +
            "FROM Exit e " +
            "RIGHT JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND FUNCTION('DATE_FORMAT', e.transactionDate, '%Y') = :year " +
            "GROUP BY FUNCTION('DATE_FORMAT', e.transactionDate, '%m/%Y')" +
            "ORDER BY FUNCTION('DATE_FORMAT', e.transactionDate, '%m/%Y') ASC")
    List<Object[]> findTotalMonthExitListByYear(@Param("budgetId") Integer budgetId, @Param("year") String year);

}
