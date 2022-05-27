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

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

     CartItem save(CartItem cartItem);
     public CartItem findCartItemById(Long cartItemId);
     public CartItem findCartItemByProductId(Long product_id);



     @Transactional
     @Modifying
     void deleteCartItemById(Long id);

     @Transactional
     void deleteCartItemByCartId(Long id);

     @Transactional
     void deleteAllByCart(Cart cart);

     @Transactional
     void deleteCartItemByProductId(Long productId);

     @Query("SELECT c FROM CartItem c join fetch c.cart s where c.status=0 and s.id=:cartId")
     List<CartItem> findCartItemsByCart(@Param("cartId") Long id);


     List<CartItem> findCartItemsByCartId(Long id);
}
