package com.sn.budgetbee.dto;

import java.beans.ConstructorProperties;
import java.util.Date;

public class FilterExitListTotalDTO {
    private Date month;
    private Double total;

    @ConstructorProperties({"mouth","total"})
    public FilterExitListTotalDTO(Date month, Double total) {
        this.month = month;
        this.total = total;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
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