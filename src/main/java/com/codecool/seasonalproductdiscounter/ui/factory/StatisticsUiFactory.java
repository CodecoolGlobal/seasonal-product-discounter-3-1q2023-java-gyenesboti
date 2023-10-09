package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.products.statistics.ProductStatistics;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.StatisticsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class StatisticsUiFactory extends UiFactoryBase{
    private final ProductStatistics productStatistics;
    private final Logger logger;

    public StatisticsUiFactory(AuthenticationService authenticationService, ProductStatistics productStatistics, Logger logger) {
        super(authenticationService);
        this.productStatistics = productStatistics;
        this.logger = logger;
    }

    @Override
    public String getUiName() {
        return "Statistics";
    }

    @Override
    public UiBase create() {
        return new StatisticsUi(getUiName(),true ,productStatistics, logger);
    }
}
