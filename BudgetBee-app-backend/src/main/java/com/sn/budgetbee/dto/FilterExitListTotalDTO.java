package com.sn.budgetbee.dto;

import java.beans.ConstructorProperties;
import java.util.Date;

public class FilterExitListTotalDTO {
    private String month;
    private Double total;

    @ConstructorProperties({"mouth","total"})
    public FilterExitListTotalDTO(String month, Double total) {
        this.month = month;
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
                "month='" + month + '\'' +
                ", total=" + total +
                '}';
    }
}