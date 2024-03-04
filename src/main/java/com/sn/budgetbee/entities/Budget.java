package com.sn.budgetbee.entities;

import jakarta.persistence.*;

@Entity
@Table(name="table_budgets")
public class Budget {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "budget")
    private Double budget;

//    @Column(name = "id_entrance")
//    private Integer idEntrance;

//    @Column(name = "idExit")
//    private Integer exit;

    public Budget() {

    }

    public Budget(Double budget){
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", budget=" + budget +
                '}';
    }
}
