package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;

import java.util.List;

public interface EntranceService {

    Entrance saveEntrance(Entrance entrance);

    Entrance findEntranceById(Integer id);

    List<Entrance> findEntrancesAll();

    EntranceDTO findEntranceById2(Integer id);

    List<EntranceDTO> findAllEntrance2();

    boolean deleteEntranceById(Integer id);

    List<EntranceDTO> entranceListByIdBudget(Integer id);

    List<FilterEntranceDTO> entranceListByCategoryAndMonth(Integer id, String month);

    List<FilterEntranceDTO> entranceListByCategoryAndYear(Integer id, String year);

    Integer entraceByMonth(Integer id, String month);

    Integer entraceByYear(Integer id, String year);
}
