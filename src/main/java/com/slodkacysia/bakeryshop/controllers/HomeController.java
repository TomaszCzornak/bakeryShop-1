package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.configuration.Authenticate;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@PreAuthorize("hasRole('ROLE_USER')")
public class HomeController {


    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;

    private final CategoryRepository categoryRepository;

    private final Authenticate authenticate;

    @Autowired
    public HomeController(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository, CategoryRepository categoryRepository, Authenticate authenticate) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
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




    @RequestMapping("/productlist")
    public String list(Model model){
        model.addAttribute("products", productRepository.findAllBy());

        return "productList";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }


}
