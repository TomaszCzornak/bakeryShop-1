package com.slodkacysia.bakeryshop.configuration;

import com.slodkacysia.bakeryshop.entity.User;
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
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userServiceimpl;

    @Autowired
    public CustomAuthenticationProvider(UserService userService, UserServiceImpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String email = authentication.getName();
        User user = null;
        if(email != null) {
            user = userServiceimpl.findUserByEmail(email);
        }
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        String password = user.getPassword();
        if(!password.equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        Collection<? extends GrantedAuthority> authorities =
                UserAuthorities.createAuthorities(user);
        return new UsernamePasswordAuthenticationToken(user, password,
                authorities);
    }
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }
}
