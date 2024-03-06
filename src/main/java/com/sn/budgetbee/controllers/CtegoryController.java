package com.sn.budgetbee.controllers;

import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CtegoryController {

    @GetMapping("/enum/exits")
    public ExitCategories[] getExitCategories(){
        return ExitCategories.values();
    }

    @GetMapping("/enum/entrances")
    public EntranceCategories[] getEntranceCategories(){
        return EntranceCategories.values();
    }

}
