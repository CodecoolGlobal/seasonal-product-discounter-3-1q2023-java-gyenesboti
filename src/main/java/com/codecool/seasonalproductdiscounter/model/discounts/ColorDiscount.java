package com.codecool.seasonalproductdiscounter.model.discounts;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;

public record ColorDiscount(String name, int rate, Color color, Season season) implements Discount {
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
        return "Color{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}

