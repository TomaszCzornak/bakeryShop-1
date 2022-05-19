package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Category;
import com.slodkacysia.bakeryshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllBy();

    Product findProductById(Long id);

    Product save(Product product);
    @Query("SELECT p FROM Product p join fetch p.category c where c.name = :category")
    List<Product> findProductByCategory(@Param("category") String category);



//    void update(Product product);
//
//    void delete(long productId);

    List<Product> getAllProductByCategory(String category);


}
