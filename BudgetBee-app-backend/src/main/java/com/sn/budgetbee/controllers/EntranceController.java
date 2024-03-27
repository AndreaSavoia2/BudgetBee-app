package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.EntranceDTO;
import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterEntranceDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.entities.Entrance;
import com.sn.budgetbee.exception.EntranceNotFoundException;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.EntranceService;
import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EntranceController{

    private final EntranceService SERVICE;

    public EntranceController(EntranceService service) {
        this.SERVICE = service;
    }

    @CrossOrigin
    @GetMapping("/entrances")
    public List<EntranceDTO> getAllEntrancesDto() {
        return SERVICE.findAllEntrance();
    }

    @CrossOrigin
    @GetMapping("/entrances/{entranceId}")
    public EntranceDTO getEntranceById(@PathVariable("entranceId") Integer id) {
        return SERVICE.findEntranceById(id);
    }

    @CrossOrigin
    @GetMapping("/entrances/totalcategory/bybudgetid/{budgetId}")
    public @ResponseBody List<FilterEntranceDTO> getTotalCategoryByBudgetId(@PathVariable("budgetId")Integer budgetId,
                                                                            @RequestParam(value = "year", required = false) String year,
                                                                            @RequestParam(value = "month", required = false) String month) {

        if(month != null && !month.isEmpty()){
            if (year != null && !year.isEmpty()){
                if(month.length() == 1){
                    month = "0"+month;
                }
                month = month + "/" + year;
                year = null;
                return SERVICE.entranceTotalByCategory(budgetId, month, year);
            }else{
                throw new EntranceNotFoundException("ERROR: PARAMETRO YEAR NON SPECIFICATO VUOTO O NULLO " + year);
            }
        }else if(year != null && !year.isEmpty()){
            month = null;
            return SERVICE.entranceTotalByCategory(budgetId, month, year);
        }else{
            throw new EntranceNotFoundException("ERROR: INSERIMENTO DATI NON CORETTI");
        }
    }

    @CrossOrigin
    @GetMapping("/entrances/total/bybudgetid/{budgetId}")
    public @ResponseBody Double getTotalEntrance(@PathVariable("budgetId")Integer budgetId,
                                                 @RequestParam(value = "year", required = false) String year,
                                                 @RequestParam(value = "month", required = false) String month) {

        if(month != null && !month.isEmpty()){
            if (year != null && !year.isEmpty()){
                if(month.length() == 1){
                    month = "0"+month;
                }
                month = month + "/" + year;
                year = null;
                return SERVICE.totalEntrance(budgetId,month,year);
            }else{
                throw new EntranceNotFoundException("ERROR: PARAMETRO YEAR NON SPECIFICATO VUOTO O NULLO " + year);
            }
        }else if(year != null && !year.isEmpty()){
            month = null;
            return SERVICE.totalEntrance(budgetId,month,year);
        }else{
            throw new EntranceNotFoundException("ERROR: INSERIMENTO DATI NON CORETTI");
        }
    }
    @CrossOrigin
    @PostMapping("/entrances")
    public Entrance setEntrance(@RequestBody Entrance entrance) {
        entrance.setId(0);
        return SERVICE.saveEntrance(entrance);
    }

    @CrossOrigin
    @PutMapping("/entrances")
    public Entrance updateEntrance(@RequestBody Entrance entrance) {
        return SERVICE.saveEntrance(entrance);
    }

    @CrossOrigin
    @DeleteMapping("/entrances/{entranceId}")
    public boolean deleteEntranceById(@PathVariable("entranceId") Integer id) {
        return SERVICE.deleteEntranceById(id);
    }

    @CrossOrigin
    @GetMapping("/entrances/filter/category")
    public @ResponseBody List<EntranceDTO> getAllentranceByCategory(@RequestParam Integer id,
                                                                    @RequestParam EntranceCategories category){
        return SERVICE.entranceListByIdBudgetAndCategory(id,category);
    }

    @CrossOrigin
    @GetMapping("/entrances/bybudgetid/{budgetId}")
    public List<EntranceDTO> getEntrancesByBudgetIdAndYearOrMonth(@PathVariable("budgetId")Integer budgetId,
                                                                  @RequestParam(value = "year", required = false) String year,
                                                                  @RequestParam(value = "month", required = false) String month,
                                                                  @RequestParam(value = "category", required = false) EntranceCategories category) {

        if(month != null && !month.isEmpty()){
            if (year != null && !year.isEmpty()){
                if(month.length() == 1){
                    month = "0"+month;
                }
                month = month + "/" + year;
                year = null;
                return SERVICE.entranceListByIdBudgetAndDate(budgetId, year, month, category);
            }else{
                throw new EntranceNotFoundException("ERROR: PARAMETRO YEAR NON SPECIFICATO VUOTO O NULLO " + year);
            }
        }else if(year != null && !year.isEmpty()){
            month = null;
            return SERVICE.entranceListByIdBudgetAndDate(budgetId, year, month , category);
        }else if(category != null && !category.isEmpty()){
            return SERVICE.entranceListByIdBudgetAndCategory(budgetId,category);
        }else{
            return SERVICE.entranceListByIdBudget(budgetId);
        }
    }

    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(EntranceNotFoundException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
