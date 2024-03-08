package com.sn.budgetbee.controllers;

import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @CrossOrigin
    @GetMapping("/enum/exits")
    public ExitCategories[] getExitCategories(){
        return ExitCategories.values();
    }

    @CrossOrigin
    @GetMapping("/enum/entrances")
    public EntranceCategories[] getEntranceCategories(){
        return EntranceCategories.values();
    }

}
