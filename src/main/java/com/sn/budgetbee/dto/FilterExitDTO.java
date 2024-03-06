package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.ExitCategories;

import java.beans.ConstructorProperties;

public class FilterExitDTO {

    private ExitCategories category;
    private Double total;

    @ConstructorProperties({"category", "total"})
    public FilterExitDTO(ExitCategories category, Double total) {
        this.category = category;
        this.total = total;
    }

    public ExitCategories getCategory() {
        return category;
    }

    public void setCategory(ExitCategories category) {
        this.category = category;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
