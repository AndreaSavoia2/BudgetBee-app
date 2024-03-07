package com.sn.budgetbee.dto;

import com.sn.budgetbee.entities.Budget;

public class UserDTO {

    private Integer id;
    private String username;
    private Budget budget;

    public UserDTO(Integer id, String username, Budget budget) {
        this.id = id;
        this.username = username;
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
