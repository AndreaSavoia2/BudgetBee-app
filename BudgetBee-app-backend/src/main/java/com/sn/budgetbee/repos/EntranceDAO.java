package com.sn.budgetbee.repos;

import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntranceDAO extends JpaRepository<Entrance, Integer> {

    @Query("SELECT e " +
            "FROM Entrance e " +
            "JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "ORDER BY FUNCTION('DATE_FORMAT', e.transactionDate, '%d/%m/%Y') DESC")
    List<Entrance> findEntranceByBudgetId(@Param("budgetId") Integer budgetId);

    @Query("SELECT e " +
            "FROM Entrance e " +
            "JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND e.category = :category " +
            "ORDER BY FUNCTION('DATE_FORMAT', e.transactionDate, '%d/%m/%Y') DESC")
    List<Entrance> findEntranceByBudgetIdAndCategory(@Param("budgetId") Integer budgetId,
                                                     @Param("category") EntranceCategories category);

    @Query("SELECT e " +
            "FROM Entrance e " +
            "JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND (:year IS NULL AND DATE_FORMAT(e.transactionDate, '%m/%Y') = :month " +
            "OR :month IS NULL AND DATE_FORMAT(e.transactionDate, '%Y') = :year) " +
            "AND (:category IS NULL OR e.category = :category) " +
            "ORDER BY FUNCTION('DATE_FORMAT', e.transactionDate, '%d/%m/%Y') DESC")
    List<Entrance> findEntranceByBudgetIdAndYearOrMonth(@Param("budgetId") Integer budgetId,
                                                 @Param("year") String year,
                                                 @Param("month") String month,
                                                 @Param("category") EntranceCategories category);
    @Query("SELECT NEW com.sn.budgetbee.dto.FilterEntranceDTO(e.category, SUM(e.transaction)) " +
            "FROM Entrance e " +
            "RIGHT JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND (:year IS NULL AND DATE_FORMAT(e.transactionDate, '%m/%Y') = :month " +
            "OR :month IS NULL AND DATE_FORMAT(e.transactionDate, '%Y') = :year) " +
            "GROUP BY e.category ")
    List<FilterEntranceDTO> findTotalEntranceByCategory(@Param("budgetId") Integer budgetId,
                                                @Param("month") String month,
                                                @Param("year") String year);

    @Query("SELECT SUM(e.transaction) " +
            "FROM Entrance e " +
            "RIGHT JOIN e.budget b " +
            "WHERE b.id = :budgetId " +
            "AND (:year IS NULL AND DATE_FORMAT(e.transactionDate, '%m/%Y') = :month " +
            "OR :month IS NULL AND DATE_FORMAT(e.transactionDate, '%Y') = :year) ")
    Double findTotalEntrance(@Param("budgetId") Integer budgetId,
                           @Param("month") String month,
                           @Param("year") String year);

}
