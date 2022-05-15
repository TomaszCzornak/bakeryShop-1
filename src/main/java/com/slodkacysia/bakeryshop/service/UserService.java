package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
