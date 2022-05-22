package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.CustomAuthenticationProvider;
import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.CartItem;
import com.slodkacysia.bakeryshop.entity.Purchase;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CartItemRepository;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/customer/cart")

public class CartController {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartController(CustomAuthenticationProvider customAuthenticationProvider, CustomerRepository customerRepository, UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @RequestMapping
    public String getCart(@AuthenticationPrincipal User activeCustomer) {
        User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        long cartId = user.getCart().getId();

        return "redirect:/user/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") Long cartId, Model model,@AuthenticationPrincipal User activeCustomer) {
        User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        System.out.println(user.getEmail());
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart(cartId);
        cartItemList.stream().forEach(System.out::println);
        Purchase purchase = new Purchase();
        purchase.setCart(user.getCart());
        model.addAttribute("fullCart", cartItemList);
        return "cartView";
    }

}
