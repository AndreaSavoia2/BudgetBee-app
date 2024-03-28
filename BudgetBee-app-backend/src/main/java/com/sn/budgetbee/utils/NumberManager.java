package com.sn.budgetbee.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class NumberManager {

    public double truncateDouble(double number, int scale){
        BigDecimal truncatedTransaction = BigDecimal.valueOf(number).setScale(scale, RoundingMode.DOWN);
        return truncatedTransaction.doubleValue();
    }

    public double assignSign(double number, boolean check){
        int checkNumber = check ? 1 : -1;
        return Math.signum(number) == checkNumber ? number * -1 : number;
    }
}
