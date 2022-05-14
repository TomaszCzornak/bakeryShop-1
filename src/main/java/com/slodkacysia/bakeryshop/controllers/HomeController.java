package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.Authenticate;
import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Category;
import com.slodkacysia.bakeryshop.entity.Purchase;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.CategoryRepository;
import com.slodkacysia.bakeryshop.repository.PurchaseRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.math.BigDecimal;
import java.util.*;


@Controller
//@Secured("ROLE_USER")
public class HomeController {


    private static final List ADMIN_ROLE = Arrays.asList("ADMIN_ROLE");
    private  final UserRepository userRepository;
    private final CartRepository cartRepository;

    private final PurchaseRepository purchaseRepository;

    private final CategoryRepository categoryRepository;

    private final Authenticate authenticate;
    @Autowired
    public HomeController(UserRepository userRepository, CartRepository cartRepository, PurchaseRepository purchaseRepository, CategoryRepository categoryRepository, Authenticate authenticate) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
        this.authenticate = authenticate;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
        if(authenticate.isAuthenticated(email,password)) {

            User user = new User("admin", "pass", Collections.unmodifiableList(ADMIN_ROLE));
            Purchase purchase = new Purchase();
            Cart cart = new Cart();
            cart.setPurchase(purchase);
            cart.setTotal_amount(BigDecimal.valueOf(0));
            cartRepository.save(cart);
            purchase.setCart(cart);
            purchaseRepository.save(purchase);
            System.out.println("hasło: " +password);
            return "redirect:/";

//            to wyrzycenia!!
        }
        return "redirect:/login?error";
    }
    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username and Password");
        }

        if (logout != null) {
            model.addAttribute("logout", "You have logged out successfully");
        }
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }


}
