package com.sn.budgetbee.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="table_budgets")
public class Budget {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "budget")
    private Double budget;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Exit> exits;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
    private List<Entrance> entrances;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "budget")
    private User user;

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
