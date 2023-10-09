package com.codecool.seasonalproductdiscounter.service.products.statistics;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class ProductStatisticsImpl implements ProductStatistics {

    private final ProductBrowser browser;

    public ProductStatisticsImpl(ProductBrowser browser) {
        this.browser = browser;
    }

    @Override
    public int totalProducts() {
        return browser.getAll().size();
    }

    @Override
    public Optional<Product> getMostExpensive() {
        return browser.getAll().stream().max(Comparator.comparing(Product::price));
    }

    @Override
    public Optional<Product> getCheapest() {
        return browser.getAll().stream().min(Comparator.comparing(Product::price));
    }

    @Override
    public OptionalDouble getAveragePrice() {
        return browser.getAll().stream().mapToDouble(Product::price).average();
    }

    @Override
    public Map<String, Double> getAveragePricesByName() {
        return browser.getAll().stream().collect(groupingBy(Product::name, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<Color, Double> getAveragePricesByColor() {
        return browser.getAll().stream().collect(groupingBy(Product::color, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<Season, Double> getAveragePricesBySeason() {
        return browser.getAll().stream().collect(groupingBy(Product::season, Collectors.averagingDouble(Product::price)));
    }

    @Override
    public Map<PriceRange, Double> getAveragePricesByPriceRange() {
        List<PriceRange> priceRanges = List.of(new PriceRange(10, 19), new PriceRange(20, 29),new PriceRange(30, 39),new PriceRange(40, 49),new PriceRange(50, 60));
        Map<PriceRange, Double> averagePricesByRanges = new HashMap<>();
        for (PriceRange range : priceRanges){
            List<Product> productsInRange = browser.getAll().stream().filter(p -> range.contains(p.price())).toList();
            double averagePrice = productsInRange.stream().collect(Collectors.averagingDouble(Product::price));
            averagePricesByRanges.put(range, averagePrice);
        }
        return averagePricesByRanges;
    }

    @Override
    public Map<String, Integer> getCountByName() {
        return browser.getAll().stream().collect(groupingBy(Product::name, summingInt(p -> 1)));
    }

    @Override
    public Map<Color, Integer> getCountByColor() {
        return browser.getAll().stream().collect(groupingBy(Product::color, summingInt(p -> 1)));
    }

    @Override
    public Map<Season, Integer> getCountBySeason() {
        return browser.getAll().stream().collect(groupingBy(Product::season, summingInt(p -> 1)));
    }

    @Override
    public Map<PriceRange, Integer> getCountByPriceRange() {
        List<PriceRange> priceRanges = List.of(new PriceRange(10, 19), new PriceRange(20, 29),new PriceRange(30, 39),new PriceRange(40, 49),new PriceRange(50, 60));
        Map<PriceRange, Integer> countInPriceRanges = new HashMap<>();
        for (PriceRange range : priceRanges){
            List<Product> productsInRange = browser.getAll().stream().filter(p -> range.contains(p.price())).toList();
            countInPriceRanges.put(range, productsInRange.size());
        }
        return countInPriceRanges;
    }
}


