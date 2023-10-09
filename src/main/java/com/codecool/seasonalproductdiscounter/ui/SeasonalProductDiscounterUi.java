package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.discounts.Discount;
import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountProvider;
import com.codecool.seasonalproductdiscounter.service.discounts.DiscountService;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeasonalProductDiscounterUi {
    private final ProductProvider productProvider;
    private final DiscountProvider discountProvider;
    private final DiscountService discountService;
    private final Scanner scanner;
    private final Logger logger;

    public SeasonalProductDiscounterUi(ProductProvider productProvider, DiscountProvider discountProvider, DiscountService discounterService, Logger logger) {
        this.productProvider = productProvider;
        this.discountProvider = discountProvider;
        this.discountService = discounterService;
        this.scanner = new Scanner(System.in);
        this.logger = logger;
    }

    public List<Offer> run() {
        System.out.println("Welcome to Seasonal Product Discounter!\n");
        printCatalog();
        printPromotions();

        System.out.println("Enter a date to see which products are discounted on that date:");
        LocalDate date = getDate();
        Month month = date.getMonth();
        Season season = getSeason(month);

        List<Offer> offers = new ArrayList<>();
        for (Product product : productProvider.getProducts()){
            double startingPrice = product.price();
            List<Discount> addedDiscounts = new ArrayList<>();
            startingPrice = startingPrice * SummerKickOff(month, addedDiscounts);
            startingPrice = startingPrice * WinterSale(month, addedDiscounts);
            startingPrice = startingPrice * ColorDiscount(product, month, addedDiscounts);
            startingPrice = startingPrice * SaleDiscount(product, season, addedDiscounts);
            startingPrice = startingPrice * OutletDiscount(product, season, addedDiscounts);
            if (!addedDiscounts.isEmpty()){
                Offer newOffer = getOffer(product, date, addedDiscounts, startingPrice);
                offers.add(newOffer);
            }
        }
        return offers;
        //printOffers(offers, date);
    }


    private void printCatalog() {
        logger.logInfo("Products catalog: ");
        for (Product product : productProvider.getProducts()){
            System.out.println(product);
        }
    }

    private void printPromotions() {
        logger.logInfo("Promotions catalog: ");
        for (Discount discount : discountProvider.getDiscounts()){
            System.out.println(discount);
        }
        System.out.println("");
    }

    private LocalDate getDate() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please type in a date! (format: YYYY-MM-DD)");
                String date = scanner.next();
                if (date.length() != 10){
                    throw new Exception();
                } else {
                    logger.logInfo("Typed in date: " + LocalDate.parse(date));
                    return LocalDate.parse(date);
                }
            } catch (Exception e){
                logger.logError("Invalid date input!");
            }
        }
    }

    private double SummerKickOff(Month month, List<Discount> addedDiscounts) {
        if (month == Month.JUNE || month == Month.JULY){
            addedDiscounts.add(discountProvider.getDiscounts().get(0));
            return 0.9;
        } else {
            return 1.0;
        }
    }

    private Season getSeason (Month month) {
        if (Season.SPRING.contains(month)){
            return Season.SPRING;
        } else if (Season.SUMMER.contains(month)){
            return Season.SUMMER;
        } else if (Season.AUTUMN.contains(month)){
            return Season.AUTUMN;
        } else if (Season.WINTER.contains(month)){
            return Season.WINTER;
        } else {
            return null;
        }
    }

    private double WinterSale(Month month, List<Discount> addedDiscounts) {
        if (month == Month.FEBRUARY){
            addedDiscounts.add(discountProvider.getDiscounts().get(1));
            return 0.9;
        } else {
            return 1.0;
        }
    }

    private double ColorDiscount (Product product, Month month, List<Discount> addedDiscounts) {
        if (Season.WINTER.contains(month) && product.color() == Color.BLUE){
            addedDiscounts.add(discountProvider.getDiscounts().get(2));
            return 0.95;
        } else if (Season.SPRING.contains(month) && product.color() == Color.GREEN){
            addedDiscounts.add(discountProvider.getDiscounts().get(3));
            return 0.95;
        } else if (Season.SUMMER.contains(month) && product.color() == Color.YELLOW){
            addedDiscounts.add(discountProvider.getDiscounts().get(4));
            return 0.95;
        } else if (Season.AUTUMN.contains(month) && product.color() == Color.BROWN){
            addedDiscounts.add(discountProvider.getDiscounts().get(5));
            return 0.95;
        } else {
            return 1.0;
        }
    }

    private double SaleDiscount(Product product, Season season, List<Discount> addedDiscounts){
        if (season == product.season().shift(3, product) || season == product.season().shift(1, product)) {
            addedDiscounts.add(discountProvider.getDiscounts().get(6));
            return 0.9;
        } else {
            return 1;
        }
    };

    private double OutletDiscount(Product product, Season season, List<Discount> addedDiscounts){
        if (season == product.season().shift(2, product)) {
            addedDiscounts.add(discountProvider.getDiscounts().get(7));
            return 0.8;
        } else {
            return 1;
        }
    };


    private Offer getOffer(Product product, LocalDate date, List<Discount> discounts, double price) {
        return discountService.getOffer(product, date, discounts, price);
    }

    /*private void printOffers(List<Offer> offers, LocalDate date) {
        logger.logInfo("Actual discount offers for given (" + date + "): ");
        for (Offer offer : offers){
            System.out.println(offer);
        }
    }*/
}
