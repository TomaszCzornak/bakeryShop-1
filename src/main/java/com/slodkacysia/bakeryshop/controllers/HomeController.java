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
import com.slodkacysia.bakeryshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;


@Controller
//@Secured("ROLE_USER")
public class HomeController {


    private final UserRepository userRepository;
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

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }




//    @RequestMapping("/login")
//    public String login(@RequestParam(value = "error", required = false) String error,
//                        @RequestParam(value = "logout", required = false) String logout, Model model) {
//        if (error != null) {
//            model.addAttribute("error", "Invalid username and Password");
//        }
//
//        if (logout != null) {
//            model.addAttribute("logout", "You have logged out successfully");
//        }
//        return "login";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }


}
