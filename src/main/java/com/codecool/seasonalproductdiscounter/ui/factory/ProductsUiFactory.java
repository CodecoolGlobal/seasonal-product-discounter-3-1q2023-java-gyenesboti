package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.products.browser.ProductBrowser;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.ProductsUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class ProductsUiFactory extends UiFactoryBase {
    private final ProductBrowser productBrowser;

    public ProductsUiFactory(AuthenticationService authenticationService, ProductBrowser productBrowser) {
        super(authenticationService);
        this.productBrowser = productBrowser;
    }

    @Override
    public String getUiName() {
        return "Products";
    }

    @Override
    public UiBase create() {
        return new ProductsUi(authenticationService, productBrowser, getUiName());
    }
}

