package com.xiela.java_full_stack_app.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
 @Id private Long id;
 private String first_name;
 private String last_name;
 private String email;
 private String password;

 public static User of(String first_name, String last_name, String email, String password){
  return new User(null, first_name, last_name, email, password);
 }

@PersistenceConstructor
 private User(Long id, String first_name, String last_name, String email, String password){
        this.id         = id;
        this.first_name = first_name;
        this.last_name  = last_name;
        this.email      = email;
        this.password   = password;
 }
 
}
