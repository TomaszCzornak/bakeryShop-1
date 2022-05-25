package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import com.slodkacysia.bakeryshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

     CartItem save(CartItem cartItem);
     public CartItem findCartItemById(long cartItemId);
     public CartItem findCartItemByProduct_id(long product_id);

     List<CartItem> findCartItemsByCartId(Long cartId);

     void deleteCartItemById(Long id);
     @Transactional
     void deleteCartItemByCartId(Long id);

     @Transactional
     void deleteAllByCart(Cart cart);

     @Transactional
     void deleteCartItemByProductId(Long productId);

     @Query("SELECT c FROM CartItem c join fetch c.cart s where c.status=0 and s.id=:cartId")
     List<CartItem> findCartItemsByCart(@Param("cartId") Long id);

}
