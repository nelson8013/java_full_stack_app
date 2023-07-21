package com.xiela.java_full_stack_app.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xiela.java_full_stack_app.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

  Optional<User> findByEmail(String email);
 
}
