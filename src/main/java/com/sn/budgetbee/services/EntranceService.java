package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Entrance;

import java.util.List;

public interface EntranceService {

    Entrance saveEntrance(Entrance entrance);

    Entrance findEntranceById(Integer id);

    List<Entrance> findEntrancesAll();

    boolean deleteEntranceById(Integer id);

    List<Entrance> entranceListByIdBudget(Integer id);

    List<Entrance> entranceListByCategoryAndMonth(Integer id, String month);

    List<Entrance> entranceListByCategoryAndYear(Integer id, String year);
}
