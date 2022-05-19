package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Purchase;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PurchaseController {

    private final CartRepository cartRepository;

    public PurchaseController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/purchase/{cartId}")
    public String createPurchase(@PathVariable("cartId")long cartId){

        Purchase purchase = new Purchase();
        Cart cart = cartRepository.getCartById(cartId);
        purchase.setCart(cart);
        User user = cart.getUser();
        purchase.setUser(user);

        return "redirect:/checkout/"+cartId;

    }

    @RequestMapping("/checkout/{cartId}")
    public String finalize(@PathVariable("cartId")Long cartId){
        return "checkout_list";
    }
}
