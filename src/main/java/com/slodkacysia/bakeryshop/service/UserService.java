package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.Buyer;

public interface UserService {
    Buyer findBuyerByEmail(String email);
    void saveBuyer(Buyer buyer);
}
