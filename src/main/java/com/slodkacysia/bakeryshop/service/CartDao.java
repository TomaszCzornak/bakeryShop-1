package com.slodkacysia.bakeryshop.service;

import com.slodkacysia.bakeryshop.entity.Product;
import com.slodkacysia.bakeryshop.entity.Purchase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
