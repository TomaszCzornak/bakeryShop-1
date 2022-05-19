package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/product_list/{product_category_name}")
    public String getProductByCategory(@PathVariable ("product_category_name") String productCategory, Model model){
        List<Product> productList = productRepository.findProductByCategory(productCategory);
        model.addAttribute("products", productList);
        System.out.println("Wydruk " + productList.toString());
        return "productListCustomer";
    }
}
