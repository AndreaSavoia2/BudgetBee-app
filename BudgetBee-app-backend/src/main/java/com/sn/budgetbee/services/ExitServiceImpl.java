package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.dto.UserDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.entities.User;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.repos.ExitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ExitDTO findExitById(Integer id) {
        Optional<Exit> result = EXIT_DAO.findById(id);
        Exit exit = null;
        ExitDTO exitDTO = null;

        if(result.isPresent()){
            exit = result.get();
            exitDTO = new ExitDTO(exit.getId(),exit.getTransaction(),exit.getDescription(), exit.getTransactionDate(), exit.getCategory());
        }else{
            throw new EntranceNotFoundException("NO ID EXIT FOUND: " + id);
        }

        return exitDTO;
    }

    @Override
    public List<ExitDTO> findAllExits() {
        List<Exit> exits = EXIT_DAO.findAll();
        List<ExitDTO> exitsDTO = new ArrayList<>();

        for (Exit exit: exits){
            exitsDTO.add(new ExitDTO(exit.getId(),exit.getTransaction(),exit.getDescription(), exit.getTransactionDate(),exit.getCategory()));
        }

        return exitsDTO;
    }

    @Override
    public boolean deleteExitById(Integer id) {
        Optional<Exit> result = EXIT_DAO.findById(id);

        if(result.isPresent()){
            EXIT_DAO.deleteById(id);
            return true;
        }else{
            throw new EntranceNotFoundException("NO ID EXIT FOUND: " + id);
        }
    }

    @Override
    public List<ExitDTO> exitListByIdBudget(Integer id) {
        List<Exit> exits = EXIT_DAO.findExitsByBudgetId(id);
        List<ExitDTO> exitsDTO = new ArrayList<>();

        for (Exit exit : exits){
            exitsDTO.add(new ExitDTO(exit.getId(),exit.getTransaction(),exit.getDescription(), exit.getTransactionDate(),exit.getCategory()));
        }
        return exitsDTO;
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
    public  Double exitByMonth(Integer id, String month) {
        return EXIT_DAO.findTotalExitByMonth(id, month);
    }

    @Override
    public  Double exitByYear(Integer id, String year) {
        return  EXIT_DAO.findTotalExitByYear(id, year);
    }

    @Override
    public List<FilterExitListTotalDTO> exitListTotalMonthByYear(Integer id, String year) {
        return EXIT_DAO.findTotalMonthExitListByYear(id,year);
    }
}
