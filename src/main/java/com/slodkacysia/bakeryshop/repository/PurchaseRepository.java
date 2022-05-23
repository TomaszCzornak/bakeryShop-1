package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    Purchase save(Purchase purchase);

    @Modifying
    @Transactional
    @Query("update Purchase p set p.paymentMethod =:paymentId  where p.cart.id = :cartId")
    void updatePurchase(@Param("cartId") Long cartId, Long paymentId);

    Purchase findPurchaseById(Long id);

    Purchase findPurchaseByCartId(Long id);

    Purchase findPurchaseByUserId(Long id);
}
