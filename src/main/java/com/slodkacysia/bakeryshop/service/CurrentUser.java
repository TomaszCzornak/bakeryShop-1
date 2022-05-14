package com.slodkacysia.bakeryshop.service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CurrentUser extends User {
    private final com.slodkacysia.bakeryshop.entity.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.slodkacysia.bakeryshop.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public com.slodkacysia.bakeryshop.entity.User getUser() {
        return user;
    }
}