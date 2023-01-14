package com.slodkacysia.bakeryshop.service;
import com.slodkacysia.bakeryshop.entity.Buyer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CurrentUser extends User {
    private final Buyer buyer;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       Buyer buyer) {
        super(username, password, authorities);
        this.buyer = buyer;
    }
    public Buyer getBuyer() {
        return buyer;
    }
}