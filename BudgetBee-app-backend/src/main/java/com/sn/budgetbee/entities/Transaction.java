package com.sn.budgetbee.entities;

import com.sn.budgetbee.utils.ExitCategories;

import java.time.LocalDateTime;

public interface Transaction <T>{

    public T getCategory();

    public void setCategory(T t);

    public Budget getBudget();

    public void setBudget(Budget budget);

    public Integer getId();

    public void setId(Integer id);

    public Double getTransaction();

    public void setTransaction(Double transaction);

    public String getDescription();

    public void setDescription(String description);

    public LocalDateTime getTransactionDate();

    public void setTransactionDate(LocalDateTime  transactionDate);

}
