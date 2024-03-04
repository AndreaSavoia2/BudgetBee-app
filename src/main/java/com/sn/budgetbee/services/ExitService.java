package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Exit;

import java.util.List;

public interface ExitService {
    Exit save(Exit budget);

    Exit findBudgetById(Integer id);

    List<Exit> findBudgetsAll();

    boolean deleteById(Integer id);
}
