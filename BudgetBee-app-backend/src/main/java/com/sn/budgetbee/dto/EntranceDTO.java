package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.EntranceCategories;

import java.util.Date;

public class EntranceDTO {

    private Integer id;
    private Double transaction;
    private String description;
    private Date transactionDate;
    private EntranceCategories category;

    public EntranceDTO(Integer id, Double transaction, String description, Date transactionDate, EntranceCategories category) {
        this.id = id;
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

}
