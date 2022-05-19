package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Delete extends CrudRepository<Cart, Integer> {
    @Override
    void delete(Cart entity);
}
