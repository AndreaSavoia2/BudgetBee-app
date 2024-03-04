package com.sn.budgetbee.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "entrance")
public class Entrance {

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

    public Entrance(){

    }

    public Entrance(Double transaction, String description, Date transactionDate){
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
        return "Entrance{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
