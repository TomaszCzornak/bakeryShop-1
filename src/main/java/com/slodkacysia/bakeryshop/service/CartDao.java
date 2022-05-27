package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CartDao {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }
}
