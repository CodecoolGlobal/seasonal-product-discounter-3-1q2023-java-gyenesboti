package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.enums.Color;
import com.codecool.seasonalproductdiscounter.model.enums.Season;
import com.codecool.seasonalproductdiscounter.model.products.Product;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;

import java.util.Map;

public class StatisticsUi extends UiBase{
    private final ProductStatistics productStatistics;
    private final Logger logger;

    public StatisticsUi(String title, boolean needsAuthentication, ProductStatistics productStatistics, Logger logger) {
        super(title, needsAuthentication);
        this.productStatistics = productStatistics;
        this.logger = logger;
    }

    public void run() {
        logger.logInfo("Statistics about products: ");
        getTheMostExpensive();
        getCheapest();
        getAveragePrice();
        getAveragePricesByColor();
        getAveragePricesBySeason();
    }

    private void getTheMostExpensive () {
        logger.logInfo("The most expensive product: ");
        System.out.println(productStatistics.getMostExpensive());
        System.out.println("");
    }

    private void getCheapest () {
        logger.logInfo("The cheapest product: ");
        System.out.println(productStatistics.getCheapest());
        System.out.println("");
    }

    private void getAveragePrice () {
        logger.logInfo("Average price: ");
        System.out.println(productStatistics.getAveragePrice());
        System.out.println("");
    }

    private void getAveragePricesByColor () {
        logger.logInfo("Average prices by colors: ");
        for (Map.Entry<Color, Double> entry : productStatistics.getAveragePricesByColor().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }

    private void getAveragePricesBySeason () {
        logger.logInfo("Average prices by seasons: ");
        for (Map.Entry<Season, Double> entry : productStatistics.getAveragePricesBySeason().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("");
    }


}


