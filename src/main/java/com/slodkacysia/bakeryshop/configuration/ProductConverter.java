//package com.slodkacysia.bakeryshop.configuration;
//
//import com.slodkacysia.bakeryshop.entity.Category;
//import com.slodkacysia.bakeryshop.entity.Product;
//import com.slodkacysia.bakeryshop.repository.CategoryRepository;
//import com.slodkacysia.bakeryshop.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//
//
//
//public class ProductConverter implements Converter<String, Product> {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//
//
//    @Override
//    public Product convert(String s) {
//        return productRepository.findProductById(Long.parseLong(s));
//    }
//}
