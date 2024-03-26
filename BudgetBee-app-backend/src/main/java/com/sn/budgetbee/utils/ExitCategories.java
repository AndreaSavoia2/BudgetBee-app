package com.sn.budgetbee.utils;

public enum ExitCategories {
    BONIFICO,
    SPESA,
    BOLLETTE,
    ASSICURAZIONI,
    ABBONAMENTI,
    CARBURANTE,
    VIAGGI,
    ISTRUZIONE,
    ABBIGLIAMENTO,
    HOBBY,
    REGALI,
    ANIMALI,
    ALTRO;

    public boolean isEmpty() {
        return values().length == 0;
    }
}
