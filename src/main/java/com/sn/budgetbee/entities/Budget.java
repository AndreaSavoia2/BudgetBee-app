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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_exit")
    private List<Exit> exit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_entrance")
    private List<Entrance> entrance;

    public Budget() {

    }

    public Budget(Double budget){
        this.budget = budget;
    }

    public List<Exit> getExit() {
        return exit;
    }

    public void setExit(List<Exit> exit) {
        this.exit = exit;
    }

    public List<Entrance> getEntrance() {
        return entrance;
    }

    public void setEntrance(List<Entrance> entrance) {
        this.entrance = entrance;
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
