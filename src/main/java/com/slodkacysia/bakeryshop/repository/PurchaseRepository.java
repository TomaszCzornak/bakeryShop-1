package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    Purchase save(Purchase purchase);

    Purchase findPurchaseById(Long id);

    Purchase findPurchaseByCartId(Long id);

    Purchase findPurchaseByUserId(Long id);
}
