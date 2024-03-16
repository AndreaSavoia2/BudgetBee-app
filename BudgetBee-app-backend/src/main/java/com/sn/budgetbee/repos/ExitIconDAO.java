package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.ExitIcon;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExitIconDAO extends JpaRepository <ExitIcon, Integer> {

    @Query("SELECT i.link FROM ExitIcon i WHERE i.exitCategoriesLink = :category")
    String findLink(@Param("category") ExitCategories category);
}
