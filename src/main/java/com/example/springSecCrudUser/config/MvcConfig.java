package com.example.springSecCrudUser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springSecCrudUser.config.interceptor.CustomInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    CustomInterceptor customInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String path="file:/home/kevinsdj/Escritorio/proyectos_java/springSecCrudUser/src/main/resources/static/";
        registry.addResourceHandler("/static/**")
                .addResourceLocations(path);

    }
}
