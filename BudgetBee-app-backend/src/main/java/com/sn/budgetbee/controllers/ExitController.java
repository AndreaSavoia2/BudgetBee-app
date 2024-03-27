package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.ExitDTO;
import com.sn.budgetbee.dto.FilterExitDTO;
import com.sn.budgetbee.dto.FilterExitListTotalDTO;
import com.sn.budgetbee.entities.Exit;
import com.sn.budgetbee.exception.ErrorResponseData;
import com.sn.budgetbee.exception.ExitNotFoundException;
import com.sn.budgetbee.services.ExitService;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExitController{

    private final ExitService SERVICE;

    public ExitController(ExitService service) {
        this.SERVICE = service;
    }

    @CrossOrigin
    @GetMapping("/exits")
    public List<ExitDTO> getAllExits() {
        return SERVICE.findAllExits();
    }

    @CrossOrigin
    @GetMapping("/exits/{exitId}")
    public ExitDTO getExitById(@PathVariable("exitId") Integer id) {
        return SERVICE.findExitById(id);
    }

    @CrossOrigin
    @GetMapping("/exits/total/month")
    public @ResponseBody  Double getExitsByMonth(@RequestParam Integer id, @RequestParam String month) { return SERVICE.exitByMonth(id, month); }

    @CrossOrigin
    @GetMapping("/exits/total/year")
    public @ResponseBody  Double getExitsByYear(@RequestParam Integer id, @RequestParam String year) { return SERVICE.exitByYear(id, year); }

    @CrossOrigin
    @PostMapping("/exits")
    public Exit setExit(@RequestBody Exit exit) {
        exit.setId(0);
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @PutMapping("/exits")
    public Exit updateExit(@RequestBody Exit exit) {
        return SERVICE.saveExit(exit);
    }

    @CrossOrigin
    @DeleteMapping("/exits/{exitId}")
    public boolean deleteExitById(@PathVariable("exitId") Integer id) {
        return SERVICE.deleteExitById(id);
    }

    //restituisce totale per ogni anno utile al grafico
    @CrossOrigin
    @GetMapping("/exits/filter/mouthlist")
    public @ResponseBody List<FilterExitListTotalDTO> getExitsTotalMouthListByYear(@RequestParam Integer id, @RequestParam String year) {
        return SERVICE.exitListTotalMonthByYear(id, year);
    }

    @CrossOrigin
    @GetMapping("/exits/filter/category")
    public @ResponseBody List<ExitDTO> getAllExitByCategory(@RequestParam Integer id, @RequestParam ExitCategories category){
        return SERVICE.exitListByIdBudgetAndCategory(id,category);
    }

    @CrossOrigin
    @GetMapping("/exits/bybudgetid/{budgetId}")
    public List<ExitDTO> getExitsByBudgetIdAndYearOrMonth(@PathVariable("budgetId")Integer budgetId, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month, @RequestParam(value = "category", required = false) ExitCategories category) {

        if(month != null && !month.isEmpty()){
            if (year != null && !year.isEmpty()){
                if(month.length() == 1){
                    month = "0"+month;
                }
                month = month + "/" + year;
                year = null;
                return SERVICE.exitListByIdBudgetAndDate(budgetId, year, month, category);
            }else{
                throw new ExitNotFoundException("ERROR: PARAMETRO YEAR NON SPECIFICATO VUOTO O NULLO " + year);
            }
        }else if(year != null && !year.isEmpty()){
            return SERVICE.exitListByIdBudgetAndDate(budgetId, year, month , category);
        }else if(category != null && !category.isEmpty()){
            return SERVICE.exitListByIdBudgetAndCategory(budgetId,category);
        }else{
            return SERVICE.exitListByIdBudget(budgetId);
        }
    }

    @CrossOrigin
    @GetMapping("/exits/totalforbudgetid/{budgetId}")
    public @ResponseBody List<FilterExitDTO> getTotalCategoryByBudgetId(@PathVariable("budgetId")Integer budgetId, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "month", required = false) String month) {

        if(month != null && !month.isEmpty()){
            if (year != null && !year.isEmpty()){
                if(month.length() == 1){
                    month = "0"+month;
                }
                month = month + "/" + year;
                year = null;
                return SERVICE.exitTotalByCategory(budgetId, month, year);
            }else{
                throw new ExitNotFoundException("ERROR: PARAMETRO YEAR NON SPECIFICATO VUOTO O NULLO " + year);
            }
        }else if(year != null && !year.isEmpty()){
            return SERVICE.exitTotalByCategory(budgetId, month, year);
        }else{
            throw new ExitNotFoundException("ERROR: INSERIMENTO DATI NON CORETTI");
        }
    }
    //Exception---------

    @ExceptionHandler
    public ResponseEntity<ErrorResponseData> handleException(ExitNotFoundException exc){

        ErrorResponseData error = new ErrorResponseData();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
