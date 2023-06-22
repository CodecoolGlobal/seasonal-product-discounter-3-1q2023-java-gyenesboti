package com.codecool.seasonalproductdiscounter.ui.factory;

import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;
import com.codecool.seasonalproductdiscounter.ui.UiBase;

public abstract class UiFactoryBase {

    protected final AuthenticationService authenticationService;

    public UiFactoryBase(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public abstract String getUiName();
    public abstract UiBase create();
}

