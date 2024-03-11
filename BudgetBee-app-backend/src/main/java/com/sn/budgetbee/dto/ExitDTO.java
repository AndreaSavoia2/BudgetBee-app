package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.CascadeType;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

public class ExitDTO {

    private Integer id;
    private Double transaction;
    private String description;
    private String transactionDate;
    private String transactionHour;
    private ExitCategories category;

    public ExitDTO(Integer id, Double transaction, String description, String transactionDate, String transactionHour, ExitCategories category) {
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

    public String getTransactionhour() {
        return transactionHour;
    }

    public void setTransactionhour(String transactionhour) {
        this.transactionHour = transactionhour;
    }

    public ExitCategories getCategory() {
        return category;
    }

    public void setCategory(ExitCategories category) {
        this.category = category;
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
}
