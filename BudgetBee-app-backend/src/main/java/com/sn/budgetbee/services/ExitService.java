package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;

import java.util.List;

public interface ExitService {
    Exit saveExit(Exit exit);

    ExitDTO findExitById(Integer id);

    List<ExitDTO> findAllExits();

    boolean deleteExitById(Integer id);

    List<ExitDTO> exitListByIdBudget(Integer id);

    List<FilterExitDTO> exitListByCategoryAndMonth(Integer id, String month);

    List<FilterExitDTO> exitListByCategoryAndYear(Integer id, String year);

    Double exitByMonth(Integer id, String month);

    Double exitByYear(Integer id, String year);

    List<FilterExitListTotalDTO> exitListTotalMonthByYear (Integer id, String year);
}
