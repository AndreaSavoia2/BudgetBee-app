package com.sn.budgetbee.repos;

import com.sn.budgetbee.entities.Exit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExitDAO extends JpaRepository<Exit, Integer> {


}
