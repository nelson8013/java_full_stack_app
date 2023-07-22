package com.xiela.java_full_stack_app.Services;


import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

public class Token {
   @Getter
   private final String token;

   private Token(String token) { 
    this.token = token ;
   }

   public static Token of(Long userId, Long validityInMinutes, String secretKey){
    var issueDate = Instant.now();
    return new Token(
                Jwts.builder()
                    .claim("user_id", userId)
                    .setIssuedAt(Date.from(issueDate))
                    .setExpiration(Date.from(issueDate.plus(validityInMinutes, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .compact());
    
    
   }


}
