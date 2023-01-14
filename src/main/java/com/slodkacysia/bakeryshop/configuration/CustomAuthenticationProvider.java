package com.slodkacysia.bakeryshop.configuration;

import com.slodkacysia.bakeryshop.entity.Buyer;
import com.slodkacysia.bakeryshop.service.UserService;
import com.slodkacysia.bakeryshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userServiceimpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserService userService, UserServiceImpl userServiceimpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceimpl = userServiceimpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String email = authentication.getName();
        Buyer buyer = null;
        if(email != null) {
            buyer = userServiceimpl.findBuyerByEmail(email);
        }
        if(buyer == null) {
            throw new UsernameNotFoundException("Incorrect User Name");
        }
        String password = buyer.getPassword();



        boolean isPasswordMatch = bCryptPasswordEncoder.matches(authentication.getCredentials().toString(),password);
        if(!isPasswordMatch){
            throw new BadCredentialsException("Incorrect credentials");
        }
        Collection<? extends GrantedAuthority> authorities =
                UserAuthorities.createAuthorities(buyer);
        return new UsernamePasswordAuthenticationToken(buyer, password,
                authorities);
    }
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }
}
