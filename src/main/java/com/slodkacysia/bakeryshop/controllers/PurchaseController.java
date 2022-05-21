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
import java.awt.print.Book;
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

    @RequestMapping("/purchase/{cartId}")
    public String createPurchase(@PathVariable("cartId")Long cartId){

        Purchase purchase = new Purchase();
        Cart cart = cartRepository.getCartById(cartId);
        purchase.setCart(cart);
        User user = cart.getUser();
        purchase.setUser(user);
        purchaseRepository.save(purchase);

        return "redirect:/checkout/"+cartId;

    }

    @RequestMapping("/checkout/{cartId}")
    public String finalize(@PathVariable("cartId")Long cartId, Model model){
        Cart cart = cartRepository.getCartById(cartId);
        List<CartItem> cartItemList = cart.getCartItems();
        model.addAttribute("checkout", cartItemList);
        System.out.println("numer koszyka " + cart.getId().toString());

        return "checkout_list";
    }

    @ModelAttribute("payment")
    public List<PaymentMethod> getPaymentMethod(){
        return paymentMethodRepository.findAllBy();
    }

    @PostMapping("/finalization/{cartId}")
    public String createOrder(@PathVariable("cartId")Long cartId, Model model, @ModelAttribute("purchase")@Valid Purchase purchase, BindingResult bindingResult){
        Cart cart = cartRepository.getCartById(cartId);
        List<CartItem> cartItemList = cart.getCartItems();
        purchase.setCart(cart);
        User user = cart.getUser();
        purchase.setUser(user);
        purchase.setPaymentMethod(purchase.getCart().getPurchase().getPaymentMethod());
        purchaseRepository.save(purchase);
        model.addAttribute("finalization", cartItemList);
        return "finalPage";
    }
}
