package com.slodkacysia.bakeryshop.service;

//import com.slodkacysia.bakeryshop.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CurrentUser extends SecurityProperties.User {
    private final com.slodkacysia.bakeryshop.entity.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.slodkacysia.bakeryshop.entity.User user) {
        super();
        this.user = user;
    }
    public com.slodkacysia.bakeryshop.entity.User getUser() {return user;}
}