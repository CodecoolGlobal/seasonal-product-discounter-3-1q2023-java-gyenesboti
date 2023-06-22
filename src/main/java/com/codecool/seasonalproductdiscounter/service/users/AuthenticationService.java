package com.codecool.seasonalproductdiscounter.service.users;

import com.codecool.seasonalproductdiscounter.model.users.User;

public interface AuthenticationService {
    boolean authenticate(User user);
}

