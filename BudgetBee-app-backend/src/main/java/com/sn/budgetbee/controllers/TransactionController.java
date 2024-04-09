package com.sn.budgetbee.controllers;

import com.sn.budgetbee.dto.TransactionDTO;
import com.sn.budgetbee.entities.Budget;
import com.sn.budgetbee.services.BudgetService;
import com.sn.budgetbee.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService SERVICE;

    @Autowired
    public TransactionController(TransactionService SERVICE) {
        this.SERVICE = SERVICE;
    }

    @CrossOrigin
    @GetMapping("/transaction/{budgetId}")
    public List<TransactionDTO> getAll(@PathVariable("budgetId") Integer id,
                                       @RequestParam(value = "max", required = false) Integer max) {
        return SERVICE.findAll(id,max);
    }
}
