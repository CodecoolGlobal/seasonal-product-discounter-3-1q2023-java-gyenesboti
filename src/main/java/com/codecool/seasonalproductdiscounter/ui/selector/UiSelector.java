package com.codecool.seasonalproductdiscounter.ui.selector;

import com.codecool.seasonalproductdiscounter.service.logger.Logger;
import com.codecool.seasonalproductdiscounter.ui.UiBase;
import com.codecool.seasonalproductdiscounter.ui.factory.UiFactoryBase;

import java.util.List;
import java.util.Scanner;

public class UiSelector {
    private final List<UiFactoryBase> factories;

    public UiSelector(List<UiFactoryBase> factories) {
        this.factories = factories;
    }

    public UiBase select() {
        System.out.println("Welcome to Seasonal Product Discounter v3");
        displayMenu();
        int chosenNumber = getIntInput();
        System.out.println(chosenNumber);
        if (chosenNumber == 1){
            return factories.get(0).create();
        } else if (chosenNumber == 2){
            return factories.get(1).create();
        } else {
            return factories.get(2).create();
        }
    }

    private void displayMenu() {
        System.out.println("Available screens: ");
        System.out.println(" 1 - Products");
        System.out.println(" 2 - Statistics");
        System.out.println(" 3 - Offers");
        System.out.println("");
    }

    private static int getIntInput() {
        while (true) {
            try {
                System.out.println("Please type in a number of an available screen!");
                Scanner scanner = new Scanner(System.in);
                int code = scanner.nextInt();
                if (code > 0 && code < 4){
                    return code;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Not a valid number!");
            }
        }
    }
}

