package com.slodkacysia.bakeryshop.controllers;


import com.slodkacysia.bakeryshop.entity.Category;
import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.entity.User;
import com.slodkacysia.bakeryshop.repository.CategoryRepository;
import com.slodkacysia.bakeryshop.repository.ProductRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import com.slodkacysia.bakeryshop.service.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final CategoryRepository categoryRepository;
    @Autowired
    AdminUser(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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
            return "redirect:/admin/productlist";
        }
    }

    @RequestMapping("/productlist")
    public String list(Model model){
        model.addAttribute("products", productRepository.findAllBy());

        return "productList";
    }
    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    @RequestMapping("/removeproduct/{id}")
    public String deleteCategory(@PathVariable Long id){
        Product product = productRepository.findProductById(id);
        productRepository.delete(product);
        return "redirect:/admin/productlist";
    }
    @RequestMapping("/editproduct/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Product product = productRepository.findProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }
    @RequestMapping(value = "/editproduct/{id}", method = RequestMethod.POST)
    public String saveEditProduct(@PathVariable Long id, Product product) {
        productRepository.save(product);

        return "redirect:/admin/productlist";
    }
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
