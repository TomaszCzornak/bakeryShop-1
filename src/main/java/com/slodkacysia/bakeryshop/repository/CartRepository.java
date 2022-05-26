package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Role;
import com.slodkacysia.bakeryshop.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    public  Cart save(Cart cart);

    public Cart getCartById(long cartId);

    Cart findCartByUser(User user);
    @Query(value = "select * from cart c join cart_item ci on ci.id = c.id", nativeQuery = true)
    public Cart findCartById(Long id);



}
