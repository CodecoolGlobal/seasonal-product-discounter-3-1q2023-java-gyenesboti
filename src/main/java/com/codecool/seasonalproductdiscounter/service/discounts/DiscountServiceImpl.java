package com.codecool.seasonalproductdiscounter.service.discounts;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    public DiscountServiceImpl () {}

    @Override
    public Offer getOffer(Product product, LocalDate date, List<Discount> discounts, double price) {
        return new Offer(product, date, discounts, price);
    }
}