package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.PaymentMethod;
import com.slodkacysia.bakeryshop.entity.Purchase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface PurchaseSpecific extends CrudRepository<Purchase, Long> {

    @Modifying
    @Query(value = "insert into purchase (cart_date,cart_id, payment_method_id, user_id) VALUES (now(),:cartId,:paymentId,:userId)", nativeQuery = true)
    @Transactional
    void updatePayment(@Param("cartId") Long cartId, @Param("paymentId") PaymentMethod paymentMethod, @Param("userId") Long id);

    }
