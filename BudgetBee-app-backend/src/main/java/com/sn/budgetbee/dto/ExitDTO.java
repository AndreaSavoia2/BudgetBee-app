package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.CascadeType;

import javax.xml.crypto.Data;
import java.util.Date;

public class ExitDTO {

    private Integer id;
    private Double transaction;
    private String description;
    private Date transactionDate;
    private ExitCategories category;

    public ExitDTO(Integer id, Double transaction, String description, Date transactionDate, ExitCategories category) {
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
