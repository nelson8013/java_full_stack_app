package com.xiela.java_full_stack_app.Services;


import com.xiela.java_full_stack_app.Model.User;
import com.xiela.java_full_stack_app.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String first_name, String last_name,String email,String password, String passwordConfirm){
        if(!Objects.equals(password, passwordConfirm)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        return userRepository.save( User.of(first_name, last_name,email, passwordEncoder.encode(password)) );
    }

    public User login(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials!");
        }
        return user;
    }

}
