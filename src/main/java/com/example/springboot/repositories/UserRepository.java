package com.example.springboot.repositories;

import com.example.springboot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    UserDetails findUserByUsername(String name);
    User getUserByUsername(String username);
    User getUserById(Long id);
}