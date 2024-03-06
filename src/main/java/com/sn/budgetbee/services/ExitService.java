package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Exit;

import java.util.List;

public interface ExitService {
    Exit saveExit(Exit exit);

    Exit findExitById(Integer id);

    List<Exit> findAllExits();

    boolean deleteExitById(Integer id);

    List<Exit> exitListByIdBudget(Integer id);

    List<FilterExitDTO> exitListByCategoryAndMonth(Integer id, String month);

    List<FilterExitDTO> exitListByCategoryAndYear(Integer id, String year);

    Integer exitByMonth(Integer id, String month);

    Integer exitByYear(Integer id, String year);
}
