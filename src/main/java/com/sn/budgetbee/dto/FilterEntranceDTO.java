package com.sn.budgetbee.dto;

import com.sn.budgetbee.utils.EntranceCategories;

import java.beans.ConstructorProperties;

public class FilterEntranceDTO {

    private EntranceCategories category;

    private Double total;

    @ConstructorProperties({"category", "total"})
    public FilterEntranceDTO(EntranceCategories category, Double total) {
        this.category = category;
        this.total = total;
    }

    public EntranceCategories getCategory() {
        return category;
    }

    public void setCategory(EntranceCategories category) {
        this.category = category;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
