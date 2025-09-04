package com.gustavoksbr.portfoliomaker.controller;

import com.gustavoksbr.portfoliomaker.controller.interceptos.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class HttpConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;
    @Autowired
    public HttpConfig(JwtInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200/",
                        "https://gustavoksbr-portfolio-maker.vercel.app/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("portfolio/save",
                        "portfolio/delete");
    }
}
