package com.ecam.picto.pictopro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages = "com.ecam.picto.pictopro")
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//On dit a spring ou se situe les css et les images
        registry.addResourceHandler("css/**","images/**","js/**").addResourceLocations("classpath:/static/css/", "classpath:/static/images/","classpath:/static/js/");
    }
}
