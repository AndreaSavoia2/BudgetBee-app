package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Entrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntranceDAO extends JpaRepository<Entrance, Integer> {

    @Query("SELECT e FROM Entrance e JOIN e.budget b WHERE b.id = :budgetId")
    List<Entrance> findEntranceByBudgetId(@Param("budgetId") Integer budgetId);
}
