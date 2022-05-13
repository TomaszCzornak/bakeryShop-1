package com.slodkacysia.bakeryshop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(CategoryConverter());
    }
    @Bean
    public Converter CategoryConverter(){
        return new CategoryConverter();
    }
}
