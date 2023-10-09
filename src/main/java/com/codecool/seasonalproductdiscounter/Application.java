package com.codecool.seasonalproductdiscounter;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.discounts.*;
import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.service.logger.LoggerBase;
import com.codecool.seasonalproductdiscounter.service.offers.*;
import com.codecool.seasonalproductdiscounter.service.products.browser.*;
import com.codecool.seasonalproductdiscounter.service.products.provider.ProductProvider;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.*;
import com.codecool.seasonalproductdiscounter.service.users.*;
import com.codecool.seasonalproductdiscounter.ui.SeasonalProductDiscounterUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.authentication.UserAuthenticator;
import com.codecool.seasonalproductdiscounter.ui.factory.OffersUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.ProductsUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.StatisticsUiFactory;
import com.codecool.seasonalproductdiscounter.ui.factory.UiFactoryBase;
import com.codecool.seasonalproductdiscounter.ui.selector.UiSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        //Services
        Logger logger = new LoggerBase();
        ProductProvider productProvider = new RandomProductGenerator(100, 10, 60);
        DiscountProvider discountProvider = new DiscountProviderImpl();
        DiscountService discounterService = new DiscountServiceImpl();
        ProductBrowser productBrowser = new ProductBrowserImpl(productProvider);
        ProductStatistics productStatistics = new ProductStatisticsImpl(productBrowser);
        SeasonalProductDiscounterUi discounter = new SeasonalProductDiscounterUi(productProvider, discountProvider, discounterService, logger);
        OfferService offerService = new OfferServiceImpl(discounter);
        AuthenticationService authenticationService = new AuthenticationServiceImpl();
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        //Factories
        ProductsUiFactory productsUiFactory = new ProductsUiFactory(authenticationService, productBrowser);
        StatisticsUiFactory statisticsUiFactory = new StatisticsUiFactory(authenticationService, productStatistics, logger);
        OffersUiFactory offersUiFactory = new OffersUiFactory(authenticationService, offerService);

        List<UiFactoryBase> factories = new ArrayList<>();
        factories.add(productsUiFactory);
        factories.add(statisticsUiFactory);
        factories.add(offersUiFactory);

        UiSelector uiSelector = new UiSelector(factories);
        UiBase ui = uiSelector.select();
        if (ui.isNeedsAuthentication()) {
            User user = ui.getUser();
            if(userAuthenticator.authenticate(user)){
                ui.displayTitle();
                ui.run();
            }
        } else {
            ui.displayTitle();
            ui.run();
        }
        System.out.println("Press any key to exit.");
        new Scanner(System.in).nextLine();

    }

}
