package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.repos.ExitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExitServiceImpl implements ExitService{

    private final ExitDAO EXIT_DAO;

    @Autowired
    public ExitServiceImpl(ExitDAO exitDAO) {
        this.EXIT_DAO = exitDAO;
    }

    @Override
    public Exit saveExit(Exit exit) {

        return EXIT_DAO.save(exit);
    }

    @Override
    public Exit findExitById(Integer id) {
        Optional<Exit> result = EXIT_DAO.findById(id);
        Exit exit = null;

        if(result.isPresent()){
            exit = result.get();
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: " + id);
        }

        return exit;
    }

    @Override
    public List<Exit> findAllExits() { return EXIT_DAO.findAll();}

    @Override
    public boolean deleteExitById(Integer id) {
        Optional<Exit> result = EXIT_DAO.findById(id);

        if(result.isPresent()){
            EXIT_DAO.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("NO ID USER FOUND ERROR: :" + id);
        }
    }

    @Override
    public List<Exit> exitListByIdBudget(Integer id) {
        return EXIT_DAO.findExitsByBudgetId(id);
    }

    @Override
    public List<FilterExitDTO> exitListByCategoryAndMonth(Integer id, String month) {
        return EXIT_DAO.findTotalExitByCategoryAndMonth(id, month);
    }

    @Override
    public List<FilterExitDTO> exitListByCategoryAndYear(Integer id, String year) {
        return EXIT_DAO.findTotalExitByCategoryAndYear(id, year);
    }

    @Override
    public Integer exitByMonth(Integer id, String month) {
        return EXIT_DAO.findTotalExitByMonth(id, month);
    }

    @Override
    public Integer exitByYear(Integer id, String year) {
        return  EXIT_DAO.findTotalExitByYear(id, year);
    }
}
