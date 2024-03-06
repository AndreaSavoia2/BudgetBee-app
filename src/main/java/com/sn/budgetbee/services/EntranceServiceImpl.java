package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.repos.EntranceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntranceServiceImpl implements EntranceService{

    private final EntranceDAO ENTRANCE_DAO;

    @Autowired
    public EntranceServiceImpl(EntranceDAO entranceDAO) {
        this.ENTRANCE_DAO = entranceDAO;
    }

    @Override
    public Entrance saveEntrance(Entrance entrance) {
        return ENTRANCE_DAO.save(entrance);
    }

    @Override
    public Entrance findEntranceById(Integer id) {
        Optional<Entrance> result = ENTRANCE_DAO.findById(id);
        Entrance entrance = null;

        if(result.isPresent()){
            entrance = result.get();
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: " + id);
        }

        return entrance;
    }

    @Override
    public List<Entrance> findEntrancesAll() {
        return ENTRANCE_DAO.findAll();
    }

    @Override
    public boolean deleteEntranceById(Integer id) {
        Optional<Entrance> result = ENTRANCE_DAO.findById(id);

        if(result.isPresent()){
            ENTRANCE_DAO.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: :" + id);
        }
    }

    @Override
    public List<Entrance> entranceListByIdBudget(Integer id) {
        return ENTRANCE_DAO.findEntranceByBudgetId(id);
    }

    @Override
    public List<FilterEntranceDTO> entranceListByCategoryAndMonth(Integer id, String month) {
        return ENTRANCE_DAO.findTotalEntranceByCategoryAndMonth(id, month);
    }

    @Override
    public List<FilterEntranceDTO> entranceListByCategoryAndYear(Integer id, String year) {
        return ENTRANCE_DAO.findTotalEntranceByCategoryAndYear(id, year);
    }

    @Override
    public Integer entraceByMonth(Integer id, String month) {
        return ENTRANCE_DAO.findTotalEntraceByMonth(id, month);
    }

    @Override
    public Integer entraceByYear(Integer id, String year) {
        return ENTRANCE_DAO.findTotalEntraceByYear(id, year);
    }

}
