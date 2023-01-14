package com.slodkacysia.bakeryshop.service;


import com.slodkacysia.bakeryshop.entity.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        Buyer buyer = userService.findBuyerByEmail(name);
        if (buyer == null) {throw new UsernameNotFoundException(name); }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        buyer.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(buyer.getEmail(), buyer.getPassword(),
                grantedAuthorities, buyer);
    }

}
