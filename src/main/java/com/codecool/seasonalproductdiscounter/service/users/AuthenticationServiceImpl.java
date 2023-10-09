package com.codecool.seasonalproductdiscounter.service.users;

import com.codecool.seasonalproductdiscounter.model.users.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationServiceImpl implements AuthenticationService{

    private HashMap<String,String> generateUsers () {
        HashMap<String,String> users = new HashMap<>();
        users.put("Botond", "qwe");
        users.put("Gergő", "asd");
        users.put("Borbála", "rtz");
        users.put("László", "fgh");
        users.put("Donát", "íyx");
        return users;
    }

    private boolean doesUsernameMatch (String key, String username) {
        return key.equals(username);
    }

    private boolean doesPasswordMatch (String value, String password) {
        return value.equals(password);
    }

    public boolean authenticate(User user) {
        HashMap<String,String> users = generateUsers();

        for (Map.Entry<String,String> entry : users.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            String username = user.userName();
            String password = user.password();
            if (doesUsernameMatch(key, username) && doesPasswordMatch(value, password)){
                return true;
            }
        }
        return false;
    }

}
