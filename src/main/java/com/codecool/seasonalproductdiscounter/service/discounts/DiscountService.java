package com.codecool.seasonalproductdiscounter.service.discounts;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;

import java.time.LocalDate;
import java.util.List;


public interface DiscountService {
    Offer getOffer(Product product, LocalDate date, List<Discount> discounts, double price);

}

