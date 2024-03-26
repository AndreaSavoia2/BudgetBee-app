package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.exception.UserNotFoundException;
import com.sn.budgetbee.repos.BudgetDAO;
import com.sn.budgetbee.repos.EntranceDAO;
import com.sn.budgetbee.repos.EntranceIconDAO;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntranceServiceImpl implements EntranceService{

    private final EntranceDAO ENTRANCE_DAO;
    private final EntranceIconDAO ICON_DAO;
    private final BudgetDAO BUDGET_DAO;

    @Autowired
    public EntranceServiceImpl(EntranceDAO ENTRANCE_DAO, EntranceIconDAO ICON_DAO, BudgetDAO BUDGET_DAO) {
        this.ENTRANCE_DAO = ENTRANCE_DAO;
        this.ICON_DAO = ICON_DAO;
        this.BUDGET_DAO = BUDGET_DAO;
    }

    @Override
    public Entrance saveEntrance(Entrance entrance) {
        double operation;

        if(entrance.getId() == 0){

            Optional<Budget> result = BUDGET_DAO.findById(entrance.getBudget().getId());
            if (result.isPresent()){
                Budget budget = result.get();
                operation = budget.getBudget();
                operation += entrance.getTransaction();
                budget.setBudget(operation);
                entrance.setBudget(budget);
                return ENTRANCE_DAO.save(entrance);

            }else {
                throw new UserNotFoundException("NO ID BUDGET FOUND: " + entrance.getId());
            }

        }else {

            Optional<Entrance> resultEntrance = ENTRANCE_DAO.findById(entrance.getId());
            Optional<Budget> resultBudget = BUDGET_DAO.findById(entrance.getBudget().getId());
            if(resultEntrance.isPresent() && resultBudget.isPresent()){
                Entrance rintegrescionEntrance = resultEntrance.get();
                Budget budget = resultBudget.get();
                operation = budget.getBudget();
                double rintegrescion =  (rintegrescionEntrance.getTransaction() * -1) + entrance.getTransaction();
                operation += rintegrescion;
                budget.setBudget(operation);
                entrance.setBudget(budget);
                return ENTRANCE_DAO.save(entrance);
            }else{
                throw new UserNotFoundException("NO ID USER FOUND: " + entrance.getId());
            }
        }
    }

    @Override
    public EntranceDTO findEntranceById(Integer id) {
        Optional<Entrance> result = ENTRANCE_DAO.findById(id);
        Entrance entrance = null;
        EntranceDTO entranceDTO = null;

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        if(result.isPresent()){
            entrance = result.get();
            String link = ICON_DAO.findLink(entrance.getCategory());
            entranceDTO = new EntranceDTO(
                    entrance.getId(),
                    entrance.getTransaction(),entrance.getDescription(),
                    entrance.getTransactionDate().format(formatterDate),
                    entrance.getTransactionDate().format(formatterHor),
                    entrance.getCategory(),
                    link);
        }else{
            throw new EntranceNotFoundException("NO ID ENTRANCE FOUND: " + id);
        }

        return entranceDTO;
    }

    @Override
    public List<EntranceDTO> findAllEntrance() {
        List<Entrance> entrances = ENTRANCE_DAO.findAll();
        List<EntranceDTO> entrancesDTO = new ArrayList<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        for (Entrance entrance: entrances){
            String link = ICON_DAO.findLink(entrance.getCategory());
            entrancesDTO.add(new EntranceDTO(
                    entrance.getId(),
                    entrance.getTransaction(),
                    entrance.getDescription(),
                    entrance.getTransactionDate().format(formatterDate),
                    entrance.getTransactionDate().format(formatterHor),
                    entrance.getCategory(),
                    link));
        }

        return entrancesDTO;
    }

    @Override
    public boolean deleteEntranceById(Integer id) {
        Optional<Entrance> result = ENTRANCE_DAO.findById(id);

        if(result.isPresent()){
            ENTRANCE_DAO.deleteById(id);
            return true;
        }else{
            throw new EntranceNotFoundException("NO ID ENTRANCE FOUND: " + id);
        }
    }

    @Override
    public List<EntranceDTO> entranceListByIdBudget(Integer id) {
        List<Entrance> entrances = ENTRANCE_DAO.findEntranceByBudgetId(id);
        List<EntranceDTO> entrancesDTO = new ArrayList<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");


        for(Entrance entrance : entrances){
            String link = ICON_DAO.findLink(entrance.getCategory());
            entrancesDTO.add(new EntranceDTO(
                    entrance.getId(),
                    entrance.getTransaction(),
                    entrance.getDescription(),
                    entrance.getTransactionDate().format(formatterDate),
                    entrance.getTransactionDate().format(formatterHor),
                    entrance.getCategory(),
                    link));
        }

        return entrancesDTO;
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
    public Double entraceByMonth(Integer id, String month) {
        return ENTRANCE_DAO.findTotalEntraceByMonth(id, month);
    }

    @Override
    public Double entraceByYear(Integer id, String year) {
        return ENTRANCE_DAO.findTotalEntraceByYear(id, year);
    }

    @Override
    public List<EntranceDTO> entranceListByIdBudgetAndCategory(Integer id, EntranceCategories category) {
        List<Entrance> entrances = ENTRANCE_DAO.findEntranceByBudgetIdAndCategory(id, category);
        List<EntranceDTO> entrancesDTO = new ArrayList<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");


        for(Entrance entrance : entrances){
            String link = ICON_DAO.findLink(entrance.getCategory());
            entrancesDTO.add(new EntranceDTO(
                    entrance.getId(),
                    entrance.getTransaction(),
                    entrance.getDescription(),
                    entrance.getTransactionDate().format(formatterDate),
                    entrance.getTransactionDate().format(formatterHor),
                    entrance.getCategory(),
                    link));
        }

        return entrancesDTO;
    }

}
