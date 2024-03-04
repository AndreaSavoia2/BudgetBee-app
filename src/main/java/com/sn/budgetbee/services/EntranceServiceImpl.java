package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.repos.EntranceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntranceServiceImpl implements EntranceService{

    private EntranceDAO entranceDAO;

    @Autowired
    public EntranceServiceImpl(EntranceDAO entranceDAO) {
        this.entranceDAO = entranceDAO;
    }

    @Override
    public Entrance saveEntrance(Entrance entrance) {
        return null;
    }

    @Override
    public Entrance findEntranceById(Integer id) {
        return null;
    }

    @Override
    public List<Entrance> findEntrancesAll() {
        return null;
    }

    @Override
    public boolean deleteEntranceById(Integer id) {
        return false;
    }
}
