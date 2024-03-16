package com.sn.budgetbee.services;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.repos.EntranceDAO;
import com.sn.budgetbee.repos.EntranceIconDAO;
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

    @Autowired
    public EntranceServiceImpl(EntranceDAO ENTRANCE_DAO, EntranceIconDAO ICON_DAO) {
        this.ENTRANCE_DAO = ENTRANCE_DAO;
        this.ICON_DAO = ICON_DAO;
    }

    @Override
    public Entrance saveEntrance(Entrance entrance) {
        return ENTRANCE_DAO.save(entrance);
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

}
