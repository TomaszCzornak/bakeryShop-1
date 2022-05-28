package com.slodkacysia.bakeryshop.controllers;


import com.slodkacysia.bakeryshop.entity.*;
import com.slodkacysia.bakeryshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminUser {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseSpecific purchaseSpecific;
    private final CartItemRepository cartItemRepository;

    private final CategoryRepository categoryRepository;
    @Autowired
    AdminUser(UserRepository userRepository, ProductRepository productRepository, PurchaseSpecific purchaseSpecific, CartItemRepository cartItemRepository, CategoryRepository categoryRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchaseSpecific = purchaseSpecific;
        this.cartItemRepository = cartItemRepository;
        this.categoryRepository = categoryRepository;

    }

    @RequestMapping("/panel")
    public String addProductForm(Model model) {

        model.addAttribute("product", new Product());

        return "addProduct";
    }
    @RequestMapping(value = "/panel", method = RequestMethod.POST)
    public String saveProductForm(@Valid Product product, BindingResult result) {

        if (result.hasErrors()) {
            return "addProduct";
        } else {
            System.out.println("wynik " + product.getCategory().toString());
            productRepository.save(product);
            return "redirect:/admin /product_list";
        }
    }

    @RequestMapping("/product_list")
    public String list(Model model){
        model.addAttribute("products", productRepository.findAllBy());

        return "productList";
    }
    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @RequestMapping("/remove_product/{id}")
    public String deleteCategory(@PathVariable Long id){
        Product product = productRepository.findProductById(id);
        productRepository.deleteProductById(product.getId());
        return "redirect:/admin/product_list";
    }
    @RequestMapping("/edit_product/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Product product = productRepository.findProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }
    @RequestMapping(value = "/edit_product/{id}", method = RequestMethod.POST)
    public String saveEditProduct(@PathVariable Long id, Product product) {
        productRepository.save(product);

        return "redirect:/admin/product_list";
    }
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping("/purchases")
    public String purchases(Model model){
        List<Purchase> purchaseList = purchaseSpecific.findAllByCartItemStatus();
        model.addAttribute("purchases", purchaseList);

        return "purchases_list";
    }

    @RequestMapping("/purchases/details/{cartId}")
    public String purchaseDetails(@PathVariable Long cartId, Model model){
        List<CartItem>cartItemList = cartItemRepository.findCartItemsByCartId(cartId);
        model.addAttribute("details", cartItemList);
        return "purchase_details";
    }

    @RequestMapping("/purchases/details/user/{userId}")
    public String clientDetails(@PathVariable Long userId, Model model){
        User user = userRepository.findUserById(userId);
        System.out.println("mój adres " + user.getEmail().toString());
        model.addAttribute("clientDetails", user);
        return "client_address";
    }
}
