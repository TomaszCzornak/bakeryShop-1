package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Buyer;
import com.slodkacysia.bakeryshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    public  Cart save(Cart cart);

    public Cart getCartById(long cartId);

    Cart findCartByBuyer(Buyer buyer);
    @Query(value = "select * from cart c join cart_item ci on ci.id = c.id", nativeQuery = true)
    public Cart findCartById(Long id);



}
