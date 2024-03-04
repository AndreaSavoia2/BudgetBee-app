package com.sn.budgetbee.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "table_exits")
public class Exit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction")
    private Double transaction;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_date")
    private Date transactionDate;

    public Exit() {

    }

    public Exit(Double transaction, String description, Date transactionDate) {
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
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

    @Override
    public String toString() {
        return "Exit{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
