package com.xiela.java_full_stack_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import com.scalablescripts.auth.interceptor.AuthorizationInterceptor;

import com.xiela.java_full_stack_app.Interceptors.AuthorizationInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

 private final AuthorizationInterceptor authorizationInterceptor;

 public WebMvcConfig(AuthorizationInterceptor authorizationInterceptor){
        this.authorizationInterceptor = authorizationInterceptor;
 } 

 @Override
 public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/user");
 }
 
}
