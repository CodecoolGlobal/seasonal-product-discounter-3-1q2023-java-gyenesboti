package com.codecool.seasonalproductdiscounter.service.products.browser;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.PriceRange;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class ProductBrowserImpl implements ProductBrowser {
    private final ProductProvider provider;

    public ProductBrowserImpl(ProductProvider provider) {
        this.provider = provider;
    }

    public List<Product> getAll() {
        return provider.getProducts();
    }

    @Override
    public List<Product> getByName(String name) {
        return provider.getProducts().stream().filter(p -> p.name().contains(name)).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  getByColor(Color color) {
        return provider.getProducts().stream().filter(p -> p.color().equals(color)).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  getBySeason(Season season) {
        return provider.getProducts().stream().filter(p -> p.season().equals(season)).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  getByPriceSmallerThan(double price) {
        return provider.getProducts().stream().filter(p -> p.price() < price).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  getByPriceGreaterThan(double price) {
        return provider.getProducts().stream().filter(p -> p.price() > price).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  getByPriceRange(double minimumPrice, double maximumPrice) {
        return provider.getProducts().stream().filter(p -> p.price() >= minimumPrice && p.price() <= maximumPrice).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Product>> groupByName() {
        return provider.getProducts().stream().collect(groupingBy(Product::name));
    }

    @Override
    public Map<Color, List<Product>> groupByColor() {
        return provider.getProducts().stream().collect(groupingBy(Product::color));
    }

    @Override
    public Map<Season, List<Product>> groupBySeason() {
        return provider.getProducts().stream().collect(groupingBy(Product::season));
    }

    @Override
    public Map<PriceRange, List<Product>> groupByPriceRange() {
        Map<PriceRange, List<Product>> productsInPriceRanges = new HashMap<>();
        List<PriceRange> priceRanges = List.of(new PriceRange(10, 19), new PriceRange(20, 29),new PriceRange(30, 39),new PriceRange(40, 49),new PriceRange(50, 60));
        for (PriceRange range : priceRanges){
            List<Product> productsInRange = provider.getProducts().stream().filter(p -> range.contains(p.price())).toList();
            productsInPriceRanges.put(range, productsInRange);
        }
        return productsInPriceRanges;
    }

    @Override
    public  List<Product>  orderByName() {
        return provider.getProducts().stream().sorted(Comparator.comparing(Product::name)).collect(Collectors.toList());
    }

    @Override
    public List<Product>  orderByColor() {
        return provider.getProducts().stream().sorted(Comparator.comparing(Product::color)).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  orderBySeason() {
        return provider.getProducts().stream().sorted(Comparator.comparing(Product::season)).collect(Collectors.toList());
    }

    @Override
    public  List<Product>  orderByPrice() {
        return provider.getProducts().stream().sorted(Comparator.comparingDouble(Product::price)).collect(Collectors.toList());
    }
}
