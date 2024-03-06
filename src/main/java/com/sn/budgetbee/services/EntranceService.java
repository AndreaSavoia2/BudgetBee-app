package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;

import java.util.List;

public interface EntranceService {

    Entrance saveEntrance(Entrance entrance);

    Entrance findEntranceById(Integer id);

    List<Entrance> findEntrancesAll();

    boolean deleteEntranceById(Integer id);

    List<Entrance> entranceListByIdBudget(Integer id);

    List<FilterEntranceDTO> entranceListByCategoryAndMonth(Integer id, String month);

    List<FilterEntranceDTO> entranceListByCategoryAndYear(Integer id, String year);

    Integer entraceByMonth(Integer id, String month);

    Integer entraceByYear(Integer id, String year);
}
