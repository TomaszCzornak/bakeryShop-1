package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.User;

public interface UserService {
    User findUserByUserName(String name);
    void saveUser(User user);
}
