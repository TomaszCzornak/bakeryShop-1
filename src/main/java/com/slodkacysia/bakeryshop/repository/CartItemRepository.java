package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import com.slodkacysia.bakeryshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

     CartItem save(CartItem cartItem);
     public CartItem findCartItemById(long cartItemId);
     public CartItem findCartItemByProduct_id(long product_id);

     void deleteCartItemById(Long id);

     void deleteAllByCart(Cart cart);
}
