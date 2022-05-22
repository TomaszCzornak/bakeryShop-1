//package com.slodkacysia.bakeryshop.configuration;
//
//import com.slodkacysia.bakeryshop.entity.Category;
//import com.slodkacysia.bakeryshop.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class CategoryConverter implements Converter<String, List<Category>> {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//
//
//    @Override
//    public List<Category> convert(String s) {
//        List<Category> result = new ArrayList<>();
//        result.add(categoryRepository.findById(Long.parseLong(s)));
//        return result;
//    }
//
//}