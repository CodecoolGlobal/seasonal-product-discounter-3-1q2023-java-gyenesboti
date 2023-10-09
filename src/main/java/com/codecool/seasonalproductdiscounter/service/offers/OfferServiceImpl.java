package com.codecool.seasonalproductdiscounter.service.offers;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.ui.SeasonalProductDiscounterUi;

import java.time.LocalDate;
import java.util.List;

public class OfferServiceImpl implements OfferService{
    private final SeasonalProductDiscounterUi seasonalProductDiscounterUi;

    public OfferServiceImpl(SeasonalProductDiscounterUi seasonalProductDiscounterUi) {
        this.seasonalProductDiscounterUi = seasonalProductDiscounterUi;
    }

    @Override
    public List<Offer> getOffers() {
        List<Offer> offers = seasonalProductDiscounterUi.run();
        return offers;
    }
}
