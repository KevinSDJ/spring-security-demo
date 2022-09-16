package com.example.springSecCrudUser.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String path="file:/home/kevinsdj/Escritorio/proyectos_java/springSecCrudUser/src/main/resources/static/";
        registry.addResourceHandler("/static/**")
                .addResourceLocations(path);

    }
}
