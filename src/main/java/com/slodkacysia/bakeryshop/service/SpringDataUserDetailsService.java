package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {
    private UserService userService;
    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userService.findUserByUserName(name);
        if (user == null) {throw new UsernameNotFoundException(name); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }
    public class CurrentUser extends User {
        private final com.slodkacysia.bakeryshop.entity.User user;
        public CurrentUser(String username, String password,
                           Collection<? extends GrantedAuthority> authorities,
                           com.slodkacysia.bakeryshop.entity.User user) {
            super(username, password, authorities);
            this.user = user;
        }
        public com.slodkacysia.bakeryshop.entity.User getUser() {return user;}
    }
}
