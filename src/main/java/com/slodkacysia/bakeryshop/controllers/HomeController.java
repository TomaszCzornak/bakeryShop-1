package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {


    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;

    private final CategoryRepository categoryRepository;


    @Autowired
    public HomeController(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
        this.categoryRepository = categoryRepository;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }


    @RequestMapping("/productlist")
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAllBy());

        return "productList";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";

    }
    @RequestMapping("/products_to_buy")
    public String addToCart(Model model) {
        List<Product> productList = productRepository.findAllBy();
        model.addAttribute("offer", productList);

        return "productList";
    }
}