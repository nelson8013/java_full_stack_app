package com.xiela.java_full_stack_app.Controllers;

import com.xiela.java_full_stack_app.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;

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

 record SignInRequest(String email,String password){}
 record SignInResponse(String token){}

 @PostMapping("/login")
 public SignInResponse login(@RequestBody SignInRequest signInRequest, HttpServletResponse response){
      var login =   authService.login( signInRequest.email(), signInRequest.password());

     Cookie cookie = new Cookie("refresh_token", login.getRefreshToken().getToken());
     cookie.setMaxAge(3600);
     cookie.setHttpOnly(true);
     cookie.setPath("/api");

     response.addCookie(cookie);
      return new SignInResponse(login.getAccessToken().getToken());
 }






}
