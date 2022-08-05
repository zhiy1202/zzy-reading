package com.zzy.config;

import com.zzy.compoent.AdminInterceptoe;
import com.zzy.compoent.MyInterceptor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    public static final  String OPTIONS = "OPTIONS";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/static/**");
        registry.addInterceptor(new AdminInterceptoe())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login");


    }

    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = ResourceUtils.getURL("classpath:").getPath();
        File file1 = new File(path);
        String path1 = file1.getParentFile().getPath();
        String medioPath = path1+"\\medio\\";
        System.out.println(medioPath);
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+medioPath);

    }
}
