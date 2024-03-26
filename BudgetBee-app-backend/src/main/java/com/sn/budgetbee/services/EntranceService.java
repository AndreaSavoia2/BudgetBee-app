package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
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

    List<FilterEntranceDTO> entranceListByCategoryAndMonth(Integer id, String month);

    List<FilterEntranceDTO> entranceListByCategoryAndYear(Integer id, String year);

    Double entraceByMonth(Integer id, String month);

    Double entraceByYear(Integer id, String year);

    List<EntranceDTO> entranceListByIdBudgetAndCategory(Integer id, EntranceCategories category);

}
