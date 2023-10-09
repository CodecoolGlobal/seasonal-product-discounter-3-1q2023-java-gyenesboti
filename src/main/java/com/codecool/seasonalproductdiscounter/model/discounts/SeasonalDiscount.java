package com.codecool.seasonalproductdiscounter.model.discounts;

import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;

public record SeasonalDiscount(String name, int rate, int seasonShift) implements Discount {
    public boolean accepts (Product product, LocalDate date){
        return false;
    }

    public String name () {
        return name;
    }

    public int rate () {
        return rate;
    }

    @Override
    public String toString() {
        return "Seasonal{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}

