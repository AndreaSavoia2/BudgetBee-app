package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.EntranceCategories;

import java.util.Date;

public class EntranceDTO {

    private Integer id;
    private Double transaction;
    private String description;
    private String transactionDate;
    private String transactionHour;
    private EntranceCategories category;

    public EntranceDTO(Integer id, Double transaction, String description, String transactionDate, String transactionHour, EntranceCategories category) {
        this.id = id;
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
        this.transactionHour = transactionHour;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTransaction() {
        return transaction;
    }

    public void setTransaction(Double transaction) {
        this.transaction = transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionHour() {
        return transactionHour;
    }

    public void setTransactionHour(String transactionHour) {
        this.transactionHour = transactionHour;
    }

    public EntranceCategories getCategory() {
        return category;
    }

    public void setCategory(EntranceCategories category) {
        this.category = category;
    }
}
