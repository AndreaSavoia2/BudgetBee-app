package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.BudgetNotFoundExceprion;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.exception.UserNotFoundException;
import com.sn.budgetbee.repos.BudgetDAO;
import com.sn.budgetbee.repos.ExitDAO;
import com.sn.budgetbee.repos.ExitIconDAO;
import com.sn.budgetbee.utils.ExitCategories;
import com.sn.budgetbee.utils.NumberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExitServiceImpl implements ExitService{

    private final ExitDAO EXIT_DAO;
    private final ExitIconDAO ICON_DAO;
    private final BudgetDAO BUDGET_DAO;
    private final NumberManager NUMBER_MANAGER;

    @Autowired
    public ExitServiceImpl(ExitDAO EXIT_DAO, ExitIconDAO ICON_DAO, BudgetDAO BUDGET_DAO, NumberManager NUMBER_MANAGER) {
        this.EXIT_DAO = EXIT_DAO;
        this.ICON_DAO = ICON_DAO;
        this.BUDGET_DAO = BUDGET_DAO;
        this.NUMBER_MANAGER = NUMBER_MANAGER;
    }

    @Override
    public Exit saveExit(Exit exit) {

        exit.setTransaction(NUMBER_MANAGER.truncateDouble(exit.getTransaction(),2));
        exit.setTransaction(NUMBER_MANAGER.assignSign(exit.getTransaction(),true));

        if(exit.getId() == 0){

            Optional<Budget> result = BUDGET_DAO.findById(exit.getBudget().getId());
            if (result.isPresent()){
                Budget budget = result.get();
                budget.setBudget(budget.getBudget() + exit.getTransaction());
                NUMBER_MANAGER.truncateDouble(budget.getBudget(),2);
                exit.setBudget(budget);
                return EXIT_DAO.save(exit);

            }else {
                throw new UserNotFoundException("NO ID BUDGET FOUND: " + exit.getId());
            }

        }else {

            Optional<Exit> resultExit = EXIT_DAO.findById(exit.getId());
            Optional<Budget> resultBudget = BUDGET_DAO.findById(exit.getBudget().getId());
            if(resultExit.isPresent() && resultBudget.isPresent()){
                Exit rintegrescionExit = resultExit.get();
                Budget budget = resultBudget.get();
                double rintegrescion =  (rintegrescionExit.getTransaction() * -1) + exit.getTransaction();
                budget.setBudget(budget.getBudget() + rintegrescion);
                NUMBER_MANAGER.truncateDouble(budget.getBudget(),2);
                exit.setBudget(budget);
                exit.setTransactionDate(rintegrescionExit.getTransactionDate());
                return EXIT_DAO.save(exit);
            }else{
                throw new ExitNotFoundException("NO ID EXIT FOUND: " + exit.getId());
            }

        }
    }

    @Override
    public boolean deleteExitById(Integer id) {

        Optional<Exit> result = EXIT_DAO.findById(id);


        if(result.isPresent()){
            Exit exit = result.get();
            Budget budget = BUDGET_DAO.findById(exit.getBudget().getId()).orElseThrow();
            budget.setBudget(NUMBER_MANAGER.truncateDouble(exit.getBudget().getBudget() - NUMBER_MANAGER.assignSign(exit.getTransaction(),true),2));
            EXIT_DAO.deleteById(id);
            return true;
        }else{
            throw new ExitNotFoundException("NO ID EXIT FOUND: " + id);
        }
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
            throw new ExitNotFoundException("NO ID EXIT FOUND: " + id);
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
    public List<FilterExitDTO> exitTotalByCategory(Integer idBudget, String month, String year) {
        return EXIT_DAO.findTotalExitByCategory(idBudget,month,year);
    }

    @Override
    public Double totalExit(Integer idBudget, String month, String year) {
        return EXIT_DAO.findTotalExit(idBudget,month,year);
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

    @Override
    public List<ExitDTO> exitListByIdBudgetAndCategory(Integer id, ExitCategories category) {
        List<Exit> exits = EXIT_DAO.findExitsByBudgetIdAndCategory(id,category);
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
    public List<ExitDTO> exitListByIdBudgetAndDate(Integer id, String year, String month, ExitCategories categories) {

            List<Exit> exits = EXIT_DAO.findExitsByBudgetIdAndYearOrMonth(id,year,month,categories);
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
}
