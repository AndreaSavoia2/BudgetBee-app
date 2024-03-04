package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Entrance;

import java.util.List;

public interface EntranceService {

    Entrance save(Entrance budget);

    Entrance findBudgetById(Integer id);

    List<Entrance> findBudgetsAll();

    boolean deleteById(Integer id);
}
