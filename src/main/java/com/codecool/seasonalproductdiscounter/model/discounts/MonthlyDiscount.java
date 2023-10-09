package com.codecool.seasonalproductdiscounter.model.discounts;

import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public record MonthlyDiscount(String name, int rate, Set<Month> months) implements Discount {
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
        return "Monthly{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
