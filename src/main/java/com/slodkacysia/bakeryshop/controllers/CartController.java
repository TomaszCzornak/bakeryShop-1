package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.CustomAuthenticationProvider;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String getCart(HttpServletRequest request) {
        Principal activeUser = request.getUserPrincipal();
//        Customer customer = customerRepository.findCustomerByCustomerName(activeUser.getName());
        System.out.println("wydruk " +activeUser.getName());
//        long cartId = customer.getCart().getId();

//        return "redirect:/customer/cart/" + cartId;
        return "redirect:/customer/cart/" + activeUser.getName();
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") long cartId, Model model) {
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
