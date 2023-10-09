package com.codecool.seasonalproductdiscounter.model.enums;

import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public enum Season {
    SPRING(Set.of(Month.MARCH, Month.APRIL, Month.MAY)),
    SUMMER(Set.of(Month.JUNE, Month.JULY, Month.AUGUST)),
    AUTUMN(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER)),
    WINTER(Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY));

    private final Set<Month> months;

    Season(Set<Month> months) {
        this.months = months;
    }

    public Set<Month> getMonths() {
        return months;
    }

    public boolean contains(Month month) {
        return months.contains(month);
    }


    public Season shift(int amount, Product product) {
        int seasonOrdinal = product.season().ordinal();
        int finalOrdinal = Season.values().length-1;
        if (seasonOrdinal + amount > finalOrdinal){
            return Season.values()[seasonOrdinal - 1];
        } else {
            return Season.values()[seasonOrdinal + amount];
        }
    }
}
