package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.EntranceCategories;

public class TransactionDTO {

    private Integer id;
    private Double transaction;
    private String description;
    private String transactionDate;
    private String transactionHour;
    private String category;
    private String iconLink;

    public TransactionDTO(Integer id, Double transaction, String description, String transactionDate, String transactionHour, String category, String iconLink) {
        this.id = id;
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
        this.transactionHour = transactionHour;
        this.category = category;
        this.iconLink = iconLink;
    }

    public TransactionDTO() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }
}
