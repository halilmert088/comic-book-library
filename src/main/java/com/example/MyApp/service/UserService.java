package com.example.MyApp.service;

import com.example.MyApp.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
}
