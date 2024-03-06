package com.sn.budgetbee.entities;

import com.sn.budgetbee.utils.EntranceCategories;
import com.sn.budgetbee.utils.ExitCategories;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "table_entrances")
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
    private String transactionDate;

    @Column(name="category")
    private EntranceCategories category;

    @ManyToOne(cascade ={CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="id_budget")
    private Budget budget;

    public Entrance(){

    }

    public Entrance(Double transaction, String description, String transactionDate){
        this.transaction = transaction;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public EntranceCategories getCategory() {
        return category;
    }

    public void setCategory(EntranceCategories category) {
        this.category = category;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
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

    @Override
    public String toString() {
        return "Entrance{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", budget=" + budget +
                '}';
    }
}
