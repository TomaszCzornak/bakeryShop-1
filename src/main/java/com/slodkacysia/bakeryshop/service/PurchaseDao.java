package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.PaymentMethod;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PurchaseDao {
    @Modifying
    @Query(value = "insert into Purchase (cartId.id, paymentMethod.id, userId.id) VALUES (:cartId,:paymentId,:userId)", nativeQuery = true)
    @Transactional
    void updatePayment(@Param("cartId") Long cartId, @Param("paymentMethod") PaymentMethod paymentMethod, @Param("userId") Long id) {

    }
}
