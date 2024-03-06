package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Exit;

import java.util.List;

public interface ExitService {
    Exit saveExit(Exit exit);

    Exit findExitById(Integer id);

    List<Exit> findAllExits();

    boolean deleteExitById(Integer id);

    List<Exit> exitListByIdBudget(Integer id);

    List<Exit> exitListByCategoryAndMonth(Integer id, String month);

    List<Exit> exitListByCategoryAndYear(Integer id, String year);
}
