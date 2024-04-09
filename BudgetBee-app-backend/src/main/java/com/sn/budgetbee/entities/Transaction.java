package com.sn.budgetbee.entities;

import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "budget_transactions_view")
public class Transaction {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "transaction")
    private Double transaction;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name="category")
    private String category;

    @Column(name="link")
    private String type;

    @Column(name="budget_id")
    private Integer budgetId;

    public Transaction(Integer id, Double transaction, String description, LocalDateTime transactionDate, String category, String type, Integer budgetId) {
        this.id = id;
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
        this.category = category;
        this.type = type;
        this.budgetId = budgetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Transaction() {
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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }
}
