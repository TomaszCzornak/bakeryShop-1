package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.CustomAuthenticationProvider;
import com.slodkacysia.bakeryshop.entity.Customer;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/customer/cart")

public class CartController {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomerRepository customerRepository;

    @Autowired
    public CartController(CustomAuthenticationProvider customAuthenticationProvider, CustomerRepository customerRepository) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customerRepository = customerRepository;
    }

    @RequestMapping
    public String getCart(@AuthenticationPrincipal User activeUser) {
        System.out.println("wydruk" + activeUser.getEmail().toString());
        Customer customer = customerRepository.findCustomerByEmail(activeUser.getEmail());
        long cartId = customer.getCart().getId();

        return "redirect:/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") long cartId, Model model) {
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
