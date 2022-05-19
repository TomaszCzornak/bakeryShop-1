package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.CustomAuthenticationProvider;
import com.slodkacysia.bakeryshop.entity.Customer;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CustomerRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/customer/cart")

public class CartController {

    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartController(CustomAuthenticationProvider customAuthenticationProvider, CustomerRepository customerRepository, UserRepository userRepository) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping
    public String getCart(@AuthenticationPrincipal User activeCustomer) {
        System.out.println("wydruk" + activeCustomer.getEmail().toString());
//        Customer customer = customerRepository.findCustomerByEmail(activeUser.getEmail());
        User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        long cartId = user.getCart().getId();

        return "redirect:/user/customer/cart/" + cartId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable(value = "cartId") long cartId, Model model) {
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
