package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.*;
import com.slodkacysia.bakeryshop.repository.CartItemRepository;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.PaymentMethodRepository;
import com.slodkacysia.bakeryshop.repository.PurchaseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PurchaseController {

    private final CartRepository cartRepository;
    private final PurchaseRepository purchaseRepository;
    private final CartItemRepository cartItemRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public PurchaseController(CartRepository cartRepository, PurchaseRepository purchaseRepository, CartItemRepository cartItemRepository, PaymentMethodRepository paymentMethodRepository) {
        this.cartRepository = cartRepository;
        this.purchaseRepository = purchaseRepository;
        this.cartItemRepository = cartItemRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

//    @RequestMapping("/purchase/{cartId}")
//    public String createPurchase(@PathVariable("cartId") Long cartId) {
//
//        Purchase purchase = new Purchase();
//        Cart cart = cartRepository.getCartById(cartId);
//        purchase.setCart(cart);
//        User user = cart.getUser();
//        purchase.setUser(user);
//        purchaseRepository.save(purchase);
//
//        return "redirect:/checkout/" + cartId;
//
//    }

    @RequestMapping("/checkout/{cartId}")
    public String finalize(@PathVariable("cartId") Long cartId, Model model) {

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart(cartId);
        model.addAttribute("checkout", cartItemList);

        return "checkout_list";
    }

    @ModelAttribute("payment")
    public List<PaymentMethod> getPaymentMethod() {
        return paymentMethodRepository.findAllBy();
    }

    @RequestMapping("/checkout/payment/{cartId}")
    public String PaymentView(Model model, @PathVariable Long cartId, Purchase purchase, BindingResult bindingresult) {
        Cart cart = cartRepository.getCartById(cartId);
        model.addAttribute("purchase", purchase);

        return "paymentMethod";
    }

    @RequestMapping(value = "/checkout/payment/{cartId}", method = RequestMethod.POST)
    private String PaymentMethod(@PathVariable Long cartId, @Valid Purchase purchase, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "paymentMethod";
        } else {
            purchaseRepository.updatePurchase(cartId,purchase.getPaymentMethod().getId());
        }
        return "redirect:/finalization/" + cartId;
    }

    @ResponseBody
    @RequestMapping("/finalization/{cartId}")
    public String createOrder(@PathVariable("cartId") Long cartId, Model model, @Valid Purchase purchase, BindingResult bindingResult) {
        Cart cart = cartRepository.getCartById(cartId);
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart(cartId);
//        purchase.setId(purchase.getId());
//        purchase.setCart(cart);
        if (bindingResult.hasErrors()) {
            return "paymentMethod";
        } else {
            for (int i = 0; i < cartItemList.size(); i++) {
                if (!(cartItemList.size() == 0)) {
                    cartItemList.get(i).setStatus(1);
                    cartItemRepository.save(cartItemList.get(i));

                    return "Twoje zamówienie zostały przyjęte do realizacji";
                }

            }

            return "Twój koszyk był pusty";

        }
    }
}
