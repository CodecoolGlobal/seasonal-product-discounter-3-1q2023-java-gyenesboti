package com.codecool.seasonalproductdiscounter;

import com.codecool.seasonalproductdiscounter.service.discounts.*;
import com.codecool.seasonalproductdiscounter.service.offers.*;
import com.codecool.seasonalproductdiscounter.service.products.browser.*;
import com.codecool.seasonalproductdiscounter.service.products.provider.RandomProductGenerator;
import com.codecool.seasonalproductdiscounter.service.products.statistics.*;
import com.codecool.seasonalproductdiscounter.service.users.*;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.*;
import com.codecool.seasonalproductdiscounter.ui.selector.UiSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        RandomProductGenerator productProvider = new RandomProductGenerator(100, 10, 50);
        ProductBrowser productBrowser = new ProductBrowserImpl(productProvider);
        ProductStatistics productStatistics = new ProductStatisticsImpl(productBrowser);
        DiscountProvider discountProvider = new DiscountProviderImpl();
        DiscountService discounterService = new DiscountServiceImpl(discountProvider);
        OfferService offerService = null;
        AuthenticationService authenticationService = null;
        ProductsUiFactory productsUiFactory = new ProductsUiFactory(authenticationService, productBrowser);


        List<UiFactoryBase> factories = new ArrayList<>();
        factories.add(productsUiFactory);


        UiSelector uiSelector = new UiSelector(factories);
        UiBase ui = uiSelector.select();
        if (ui.authenticate())
        {
            ui.displayTitle();
            ui.run();
        }
        System.out.println("Press any key to exit.");
        new Scanner(System.in).nextLine();
    }

}
