package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.*;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final PurchaseSpecific purchaseSpecific;

    public PurchaseController(CartRepository cartRepository, PurchaseRepository purchaseRepository, CartItemRepository cartItemRepository, PaymentMethodRepository paymentMethodRepository, PurchaseSpecific purchaseSpecific) {
        this.cartRepository = cartRepository;
        this.purchaseRepository = purchaseRepository;
        this.cartItemRepository = cartItemRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.purchaseSpecific = purchaseSpecific;
    }



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
    private String PaymentMethod(@PathVariable Long cartId, @Valid Purchase purchase, BindingResult bindingResult, @AuthenticationPrincipal User activeUser) {
        if (bindingResult.hasErrors()) {
            return "paymentMethod";
        } else {
//            purchaseRepository.updatePurchase(cartId,purchase.getPaymentMethod());
            purchaseSpecific.updatePayment(cartId,purchase.getPaymentMethod(), activeUser.getId());
        }
        return "redirect:/finalization/" + cartId;
    }

    @ResponseBody
    @RequestMapping("/finalization/{cartId}")
    public String createOrder(@PathVariable("cartId") Long cartId, Model model, @Valid Purchase purchase, BindingResult bindingResult, User activeCustomer) {
        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCart(cartId);

        if (bindingResult.hasErrors()) {
            return "paymentMethod";
        } else {
            if (!(cartItemList.size() == 0)) {
                for (int i = 0; i < cartItemList.size(); i++) {
                    cartItemList.get(i).setStatus(1);

                    cartItemRepository.save(cartItemList.get(i));
                }

                return "Twoje zamówienie zostały przyjęte do realizacji";
            }

            return "Twój koszyk był pusty";

        }
    }
}
