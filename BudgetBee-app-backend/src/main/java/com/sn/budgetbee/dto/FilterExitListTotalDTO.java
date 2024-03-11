package com.sn.budgetbee.dto;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Date;

public class FilterExitListTotalDTO {
    private String transactionDate;
    private Double total;

    @ConstructorProperties({"transactionDate","total"})
    public FilterExitListTotalDTO(String transactionDate, Double total) {
        this.transactionDate = transactionDate;
        this.total = total;
    }

    public String getMonth() {
        return transactionDate;
    }

    public void setMonth(String month) {
        this.transactionDate = month;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "FilterExitListTotalDTO{" +
                "month='" + transactionDate + '\'' +
                ", total=" + total +
                '}';
    }
}