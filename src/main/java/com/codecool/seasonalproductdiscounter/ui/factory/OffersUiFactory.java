package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.OffersUi;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public class OffersUiFactory extends UiFactoryBase{
    private final OfferService offerService;

    public OffersUiFactory(AuthenticationService authenticationService, OfferService offerService) {
        super(authenticationService);
        this.offerService = offerService;
    }

    @Override
    public String getUiName() {
        return "Offers";
    }

    @Override
    public UiBase create() {
        return new OffersUi(getUiName(), false, offerService);
    }
}
