package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.Role;
import com.slodkacysia.bakeryshop.entity.Buyer;
import com.slodkacysia.bakeryshop.repository.RoleRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    public Buyer findBuyerByEmail(String email) {
        return userRepository.findBuyerByEmail(email);
    }

    @Override
    public void saveBuyer(Buyer buyer) {
        buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
        buyer.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        buyer.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(buyer);
    }
}