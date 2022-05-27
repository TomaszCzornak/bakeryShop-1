package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.CustomAuthenticationProvider;
import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import com.slodkacysia.bakeryshop.entity.Purchase;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/user/customer/cart")

public class CartController {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public CartController(CustomAuthenticationProvider customAuthenticationProvider, UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, PurchaseRepository purchaseRepository) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @RequestMapping
    public String getCart(@AuthenticationPrincipal User activeCustomer) {
        User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        long cartId = user.getCart().getId();

        return "redirect:/user/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") Long cartId, Model model,@AuthenticationPrincipal User activeCustomer) {
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart(cartId);
        BigDecimal allCartItems = BigDecimal.valueOf(0);
        BigDecimal totalCart = BigDecimal.valueOf(0);
        for (int i = 0; i < cartItemList.size(); i++) {
            allCartItems =  allCartItems.add(BigDecimal.valueOf(cartItemList.get(i).getPrice().doubleValue()));
            totalCart = allCartItems.multiply(cartItemList.get(i).getQuantity());

        }
        Cart cart = cartRepository.getCartById(cartId);
        cart.setTotal_amount(totalCart);
        cartRepository.save(cart);
        model.addAttribute("fullCart", cartItemList);
        return "cartView";
    }

}
