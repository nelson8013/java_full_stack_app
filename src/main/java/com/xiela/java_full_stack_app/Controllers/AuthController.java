package com.xiela.java_full_stack_app.Controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiela.java_full_stack_app.Model.User;
import com.xiela.java_full_stack_app.Repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class AuthController {

 @Autowired
 private final UserRepository userRepository;

 public AuthController(UserRepository userRepository){

  this.userRepository = userRepository;
 }

 @GetMapping("/hello")
 public String hello(){
  return "Hello";
 }


 record RegisterRequest(String first_name, String last_name,String email,String password,@JsonProperty("password_confirm") String passwordConfirm){}
 record ResponseRequest(Long id, String first_name, String last_name,String email){}

 @PostMapping("/register")
 public ResponseRequest register(@RequestBody RegisterRequest registerRequest) {
  if(!Objects.equals(registerRequest.password(), registerRequest.passwordConfirm())){
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Passwords do not match");
  }

    var user = userRepository.save(User.of( registerRequest.first_name(), registerRequest.last_name(), registerRequest.email(), registerRequest.password() ) );
    return new ResponseRequest(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmail());
 }








}