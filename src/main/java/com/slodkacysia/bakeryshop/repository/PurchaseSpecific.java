package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.PaymentMethod;
import com.slodkacysia.bakeryshop.entity.Purchase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PurchaseSpecific extends CrudRepository<Purchase, Long> {

    @Modifying
    @Query(value = "insert into purchase (cart_date,cart_id, payment_method_id, buyer_id) VALUES (now(),:cartId,:paymentId,:BuyerId)", nativeQuery = true)
    @Transactional
    void updatePayment(@Param("cartId") Long cartId, @Param("paymentId") PaymentMethod paymentMethod, @Param("BuyerId") Long id);


    @Query(value = "Select * from purchase p join cart  c on p.cart_id = c.id  join cart_item  ci on c.id = ci.cart_id where ci.status = 1", nativeQuery =true)
    List<Purchase>findAllByStatus();

    @Query(value = "Select * from purchase p ", nativeQuery = true)
    List<Purchase>findAllByCartItemStatus();
    }
