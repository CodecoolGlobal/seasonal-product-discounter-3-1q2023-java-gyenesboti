package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;

import java.util.Scanner;

public abstract class UiBase {
    private final AuthenticationService authenticationService;
    private final String title;

    protected UiBase(AuthenticationService authenticationService, String title) {
        this.title = title;
        this.authenticationService = authenticationService;
    }

    protected static String getTextInput(String text) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(text);
            input = new Scanner(System.in).nextLine();
        }

        return input;
    }

    protected User getUser() {
        // ...
        return null;
    }

    public void displayTitle() {
        System.out.println(title);
    }

    public boolean authenticate() {
        User user = getUser();
        // ...
        return false;
    }

    public abstract void run();
}


