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
import com.sn.budgetbee.utils.NumberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntranceServiceImpl implements EntranceService{

    private final EntranceDAO ENTRANCE_DAO;
    private final EntranceIconDAO ICON_DAO;
    private final BudgetDAO BUDGET_DAO;
    private final NumberManager NUMBER_MANAGER;

    @Autowired
    public EntranceServiceImpl(EntranceDAO ENTRANCE_DAO,
                               EntranceIconDAO ICON_DAO,
                               BudgetDAO BUDGET_DAO,
                               NumberManager NUMBER_MANAGER) {
        this.ENTRANCE_DAO = ENTRANCE_DAO;
        this.ICON_DAO = ICON_DAO;
        this.BUDGET_DAO = BUDGET_DAO;
        this.NUMBER_MANAGER = NUMBER_MANAGER;
    }

    @Override
    public Entrance saveEntrance(Entrance entrance) {

        entrance.setTransaction(NUMBER_MANAGER.truncateDouble(entrance.getTransaction(),2));
        entrance.setTransaction(NUMBER_MANAGER.assignSign(entrance.getTransaction(),false));

        if(entrance.getId() == 0){

            Optional<Budget> result = BUDGET_DAO.findById(entrance.getBudget().getId());
            if (result.isPresent()){
                Budget budget = result.get();
                budget.setBudget(budget.getBudget() + entrance.getTransaction());
                NUMBER_MANAGER.truncateDouble(budget.getBudget(),2);
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
                double rintegrescion =  (rintegrescionEntrance.getTransaction() * -1) + entrance.getTransaction();
                budget.setBudget(budget.getBudget() + rintegrescion);
                NUMBER_MANAGER.truncateDouble(budget.getBudget(),2);
                entrance.setBudget(budget);
                entrance.setTransactionDate(rintegrescionEntrance.getTransactionDate());
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
            Entrance entrance = result.get();
            Budget budget = BUDGET_DAO.findById(entrance.getBudget().getId()).orElseThrow();
            budget.setBudget(NUMBER_MANAGER.truncateDouble(entrance.getBudget().getBudget() - NUMBER_MANAGER.assignSign(entrance.getTransaction(),false),2));
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
    public List<FilterEntranceDTO> entranceTotalByCategory(Integer idBudget, String month, String year) {
        return ENTRANCE_DAO.findTotalEntranceByCategory(idBudget,month,year);
    }

    @Override
    public Double totalEntrance(Integer idBudget, String month, String year) {
        return ENTRANCE_DAO.findTotalEntrance(idBudget,month,year);
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

    @Override
    public List<EntranceDTO> entranceListByIdBudgetAndDate(Integer id, String year, String month, EntranceCategories category) {

        List<Entrance> entrances = ENTRANCE_DAO.findEntranceByBudgetIdAndYearOrMonth(id,year,month,category);
        List<EntranceDTO> entrancesDTO = new ArrayList<>();

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHor = DateTimeFormatter.ofPattern("HH:mm");

        for (Entrance entrance : entrances){
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
