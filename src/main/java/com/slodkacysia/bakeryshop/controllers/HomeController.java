package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Cart;
import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {



    private final ProductRepository productRepository;



    @Autowired
    public HomeController(ProductRepository productRepository) {

        this.productRepository = productRepository;

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(@AuthenticationPrincipal User activeUser) {
        return "Jesteś zalogowany jako: " + activeUser.getUserName();
    }


    @RequestMapping("/product_list")
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAllBy());

        return "productList";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model,@AuthenticationPrincipal User activeUser) {

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