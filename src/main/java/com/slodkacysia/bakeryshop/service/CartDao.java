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
    @Transactional
    public void insertWithQuery(Purchase purchase) {
        entityManager.createNativeQuery("INSERT INTO person (id, first_name, last_name) VALUES (?,?,?)")
                .setParameter(1, person.getId())
                .setParameter(2, person.getFirstName())
                .setParameter(3, person.getLastName())
                .executeUpdate();
    }
}
