package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.repository.CartItemRepository;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.ProductRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/rest/cart/")
public class CartItemController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @RequestMapping("/{cartId}")
    public @ResponseBody Cart getCartById(@PathVariable("cartId") int cartId) {

        return cartRepository.getCartById(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    public void addItem(@PathVariable("productId") int productId, @AuthenticationPrincipal User activeCustomer) {

        com.slodkacysia.bakeryshop.entity.User user = userRepository.findUserByEmail(activeCustomer.getUsername());
        Cart cart = user.getCart();
        Product product = productRepository.findProductById((long) productId);

        List<CartItem> cartItems = cart.getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {

            if (product.getId() == cartItems.get(i).getProduct().getId()) {
                CartItem cartItem = cartItems.get(i);
                BigDecimal productPrice = new BigDecimal(String.valueOf(product.getPrice()));
                BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
                cartItem.setQuantity(cartItem.getQuantity()+1);
                BigDecimal total_price = product.getPrice().multiply(cartItem.getQuantity());
                cartItem.setTotal_price(total_price);
                cartItemRepository.save(cartItem);

                return;
            }
