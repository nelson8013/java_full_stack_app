package com.xiela.java_full_stack_app.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {
    UserDetails loadUserByEmail(String email);
}
