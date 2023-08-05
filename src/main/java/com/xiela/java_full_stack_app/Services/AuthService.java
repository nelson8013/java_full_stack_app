 package com.xiela.java_full_stack_app.Services;


import com.xiela.java_full_stack_app.Errors.UserNotFoundError;
import com.xiela.java_full_stack_app.Model.User;
import com.xiela.java_full_stack_app.Repositories.UserRepository;

import lombok.var;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String accessTokenSecret;
    private final String refreshTokenSecret;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${application.security.access-token-secret}")  String accessTokenSecret,
            @Value("${application.security.refresh-token-secret}") String refreshTokenSecret){
        this.userRepository     = userRepository;
        this.passwordEncoder    = passwordEncoder;
        this.accessTokenSecret  = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
    }

    public User register(String first_name, String last_name,String email,String password, String passwordConfirm){
        if(!Objects.equals(password, passwordConfirm)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        return userRepository.save( User.of(first_name, last_name,email, passwordEncoder.encode(password)) );
    }

    /* 
    public Token login(String email, String password) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials!");
        }
        return Token.of(user.getId(), 10L, "very_long_and_secure_access_secret_key");
    }
    */

    public Login login(String email, String password) {
        User user = userRepository.findByEmail(email)
                                 .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Credentials!");
        }

        return Login.of(user.getId(), accessTokenSecret, refreshTokenSecret);
    }

    public User getUserFromToken(String token) {
        return userRepository.findById(Token.from(token, accessTokenSecret))
                .orElseThrow( UserNotFoundError::new);
    }

    public Login refreshAccess(String refreshToken) {
      var userId = Token.from(refreshToken, refreshTokenSecret);
      return  Login.of(userId, accessTokenSecret, Token.of(refreshToken));
    }
}
