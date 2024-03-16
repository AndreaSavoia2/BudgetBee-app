package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.repos.ExitDAO;
import com.sn.budgetbee.repos.ExitIconDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExitServiceImpl implements ExitService{

    private final ExitDAO EXIT_DAO;
    private  final ExitIconDAO ICON_DAO;

    @Autowired
    public ExitServiceImpl(ExitDAO EXIT_DAO, ExitIconDAO ICON_DAO) {
        this.EXIT_DAO = EXIT_DAO;
        this.ICON_DAO = ICON_DAO;
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

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        if(result.isPresent()){
            exit = result.get();
            String link = ICON_DAO.findLink(exit.getCategory());
            exitDTO = new ExitDTO(
                    exit.getId(),
                    exit.getTransaction(),
                    exit.getDescription(),
                    exit.getTransactionDate().format(formatterDate),
                    exit.getTransactionDate().format(formatterHor),
                    exit.getCategory(),
                    link);
        }else{
            throw new EntranceNotFoundException("NO ID EXIT FOUND: " + id);
        }

        return exitDTO;
    }

    @Override
    public List<ExitDTO> findAllExits() {
        List<Exit> exits = EXIT_DAO.findAll();
        List<ExitDTO> exitsDTO = new ArrayList<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        for (Exit exit: exits){
            String link = ICON_DAO.findLink(exit.getCategory());
            exitsDTO.add(new ExitDTO(
                    exit.getId(),
                    exit.getTransaction(),
                    exit.getDescription(),
                    exit.getTransactionDate().format(formatterDate),
                    exit.getTransactionDate().format(formatterHor),
                    exit.getCategory(),
                    link));
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

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        for (Exit exit : exits){
            String link = ICON_DAO.findLink(exit.getCategory());
            exitsDTO.add(new ExitDTO(
                    exit.getId(),
                    exit.getTransaction(),
                    exit.getDescription(),
                    exit.getTransactionDate().format(formatterDate),
                    exit.getTransactionDate().format(formatterHor),
                    exit.getCategory(),
                    link));
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
        List<Object[]> resultList = EXIT_DAO.findTotalMonthExitListByYear(id, year);
        List<FilterExitListTotalDTO> dtoList = new ArrayList<>();

        for (Object[] result : resultList) {
            String dateString = (String) result[0];
            Double transactionSum = (Double) result[1];
            FilterExitListTotalDTO dto = new FilterExitListTotalDTO(dateString, transactionSum);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
