package com.xiela.java_full_stack_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String path = "/api";
    private final String[] ALLOWED_URLS = {
            path+"/hello", path+"/register",path+"/login", "/**"
    };

    @Bean
     protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
       http
               .csrf()
               .disable().
               authorizeHttpRequests()
               .requestMatchers(ALLOWED_URLS)
               .permitAll();
       return http.build();
     }
}
