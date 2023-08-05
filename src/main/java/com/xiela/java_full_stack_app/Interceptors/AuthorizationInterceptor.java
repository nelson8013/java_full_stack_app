package com.xiela.java_full_stack_app.Interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.xiela.java_full_stack_app.Errors.NoBearerTokenError;
import com.xiela.java_full_stack_app.Services.AuthService;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

 private final AuthService authservice;

 public AuthorizationInterceptor(AuthService authservice){
  this.authservice = authservice;
 }

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ){
  String authorizationHeader = request.getHeader("Authorization");

  if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
    throw new NoBearerTokenError();
  }

  request.setAttribute("user", authservice.getUserFromToken(authorizationHeader.substring(7)));

  return true;
 }
 

}
