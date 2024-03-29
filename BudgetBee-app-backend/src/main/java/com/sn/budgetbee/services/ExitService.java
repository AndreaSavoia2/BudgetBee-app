package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.utils.ExitCategories;

import java.util.List;

public interface ExitService {
    Exit saveExit(Exit exit);

    ExitDTO findExitById(Integer id);

    List<ExitDTO> findAllExits();

    boolean deleteExitById(Integer id);

    List<ExitDTO> exitListByIdBudget(Integer id);

    List<FilterExitDTO> exitTotalByCategory(Integer idBudget, String month, String year);

    Double totalExit(Integer idBudget, String month, String year);

    List<FilterExitListTotalDTO> exitListTotalMonthByYear (Integer id, String year);

    List<ExitDTO> exitListByIdBudgetAndCategory(Integer id, ExitCategories category);

    List<ExitDTO> exitListByIdBudgetAndDate(Integer id, String year, String month, ExitCategories category);
}
