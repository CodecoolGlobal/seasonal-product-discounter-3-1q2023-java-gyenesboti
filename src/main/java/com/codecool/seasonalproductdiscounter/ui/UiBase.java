package com.codecool.seasonalproductdiscounter.ui;

import com.codecool.seasonalproductdiscounter.model.users.User;
import com.codecool.seasonalproductdiscounter.service.users.AuthenticationService;

import java.util.Scanner;

public abstract class UiBase {
    private final String title;
    private final boolean needsAuthentication;

    protected UiBase(String title, boolean needsAuthentication) {
        this.title = title;
        this.needsAuthentication = needsAuthentication;
    }

    protected static String getTextInput(String text) {
        String input = "";

        while (input.isEmpty()) {
            System.out.print(text);
            input = new Scanner(System.in).nextLine();
        }

        return input;
    }

    public User getUser() {
        Scanner scanner = new Scanner(System.in);
        String username = getTextInput("Please enter your username!");
        String password = getTextInput("Please enter your password!");
        return new User(username, password);
    }

    public boolean isNeedsAuthentication() {
        return needsAuthentication;
    }

    public void displayTitle() {
        System.out.println(title);
    }

    public abstract void run();
}


