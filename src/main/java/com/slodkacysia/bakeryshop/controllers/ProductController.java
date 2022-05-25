package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/product_list/{product_category_name}")
    public String getProductByCategory(@PathVariable ("product_category_name") String productCategory, Model model){
        List<Product> productList = productRepository.findAllByCategory(productCategory);
        model.addAttribute("products", productList);

        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getName().toString());
        }
        return "productListCustomer";
    }
}
