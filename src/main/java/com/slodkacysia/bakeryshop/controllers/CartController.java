package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Customer;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/cart")

public class CartController {


    private final CustomerRepository customerRepository;

    @Autowired
    public CartController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping
    public String getCart(@AuthenticationPrincipal User activeUser) {
        Customer customer = customerRepository.findCustomerByUsername(activeUser.getUsername());
        long cartId = customer.getCart().getId();

        return "redirect:/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") long cartId, Model model) {
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
