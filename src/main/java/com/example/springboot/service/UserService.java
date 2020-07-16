package com.example.springboot.service;

import com.example.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);
    boolean save (User user);
    void delete(Long id);
    List<User> findAll();
    boolean saveNew(User user);
    User getUserById(Long id);
}
