package com.sn.budgetbee.services;

import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.repos.ExitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExitServiceImpl implements ExitService{

    private ExitDAO exitDAO;

    @Autowired
    public ExitServiceImpl(ExitDAO exitDAO) {
        this.exitDAO = exitDAO;
    }

    @Override
    public Exit saveExit(Exit exit) {
        return null;
    }

    @Override
    public Exit findExitById(Integer id) {
        return null;
    }

    @Override
    public List<Exit> findAllExits() {
        return null;
    }

    @Override
    public boolean deleteExitById(Integer id) {
        return false;
    }
}
