package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

     CartItem save(CartItem cartItem);
      CartItem findCartItemById(Long cartItemId);
      CartItem findCartItemByProductId(Long product_id);



     @Transactional
     @Modifying
     void deleteCartItemById(Long id);



     @Transactional
     void deleteAllByCart(Cart cart);


     @Query("SELECT c FROM CartItem c join fetch c.cart s where c.status=0 and s.id=:cartId")
     List<CartItem> findCartItemsByCart(@Param("cartId") Long id);


     List<CartItem> findCartItemsByCartId(Long id);
}
