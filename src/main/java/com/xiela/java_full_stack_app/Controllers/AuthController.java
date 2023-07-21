package com.xiela.java_full_stack_app.Controllers;

import java.util.Objects;

import com.xiela.java_full_stack_app.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiela.java_full_stack_app.Model.User;

@RestController
@RequestMapping("/api")
public class AuthController {
 private final AuthService authService;

 public AuthController(AuthService authService){
  this.authService = authService;
 }

 /*
 * The record feature is the DTO that carries data across all layers of the app
 * */
 record RegisterRequest(String first_name, String last_name,String email,String password,@JsonProperty("password_confirm") String passwordConfirm){}
 record RegisterResponse(Long id, String first_name, String last_name,String email){}

 @PostMapping("/register")
 public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {

    var user = authService.register(
            registerRequest.first_name(),
            registerRequest.last_name(),
            registerRequest.email(),
            registerRequest.password(),
            registerRequest.passwordConfirm()
    );
    return new RegisterResponse(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmail());
 }








}
