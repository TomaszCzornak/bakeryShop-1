package com.slodkacysia.bakeryshop.configuration;

import com.slodkacysia.bakeryshop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public class UserAuthorities {
        private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils.createAuthorityList("ROLE_ADMIN",
                "ROLE_USER");
        private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");

        public static Collection<? extends GrantedAuthority> createAuthorities(User user) {
            String username = user.getEmail();
            if (username.startsWith("admin")) {
                return ADMIN_ROLES;
            }
            return USER_ROLES;
        }

        private UserAuthorities() {
        }

    }
