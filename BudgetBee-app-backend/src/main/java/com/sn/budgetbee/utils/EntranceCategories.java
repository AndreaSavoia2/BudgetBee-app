package com.sn.budgetbee.utils;

public enum EntranceCategories {
    BONIFICO,
    RIMBORSI,
    REGALI,
    STIPENDIO,
    ALTRO;

    public boolean isEmpty() {
        return values().length == 0;
    }
}
