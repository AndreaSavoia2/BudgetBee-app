package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.EntranceIcon;
import com.sn.budgetbee.utils.EntranceCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntranceIconDAO extends JpaRepository<EntranceIcon, Integer> {

    @Query("SELECT i.link FROM EntranceIcon i WHERE i.entranceCategoriesLink = :category")
    String findLink(@Param("category") EntranceCategories category);
}
