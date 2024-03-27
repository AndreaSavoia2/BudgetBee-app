package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;

import java.util.List;

public interface EntranceService {

    Entrance saveEntrance(Entrance entrance);

    EntranceDTO findEntranceById(Integer id);

    List<EntranceDTO> findAllEntrance();

    boolean deleteEntranceById(Integer id);

    List<EntranceDTO> entranceListByIdBudget(Integer id);

    List<FilterEntranceDTO> entranceTotalByCategory(Integer idBudget, String month, String year);

    Double totalEntrance(Integer idBudget, String month, String year);
    List<EntranceDTO> entranceListByIdBudgetAndCategory(Integer id, EntranceCategories category);

    List<EntranceDTO> entranceListByIdBudgetAndDate(Integer id, String year, String month, EntranceCategories category);

}
