package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Buyer, Long> {

    Buyer save(Buyer buyer);
    Buyer findByUserName(String userName);
    List<Buyer> getAllBy();
    Buyer findBuyerByEmail(String email);
    Buyer findBuyerById(Long id);


}
