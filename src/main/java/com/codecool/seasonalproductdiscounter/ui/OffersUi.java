package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.offers.Offer;
import com.codecool.seasonalproductdiscounter.service.offers.OfferService;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;

import java.util.List;

public class OffersUi extends UiBase{
    private final OfferService offerService;

    public OffersUi(String title, boolean needsAuthentication, OfferService offerService) {
        super(title, needsAuthentication);
        this.offerService = offerService;
    }

    @Override
    public void run() {
        printOffers("All offers: ", offerService.getOffers());
    }

    private void printOffers(String text, List<Offer> offers) {
        System.out.println(text + ": ");
        for (Offer offer : offers){
            System.out.println(offer);
        }
    }
}
