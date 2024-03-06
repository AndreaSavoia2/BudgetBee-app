package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExitDAO extends JpaRepository<Exit, Integer> {

    @Query("SELECT x FROM Exit x JOIN x.budget b WHERE b.id = :budgetId")
    List<Exit> findExitsByBudgetId(@Param("budgetId") Integer budgetId);

}
