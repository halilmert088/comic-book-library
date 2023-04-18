package com.example.MyApp.controller;

import com.example.MyApp.repository.UserRepository;
import com.example.MyApp.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    //Search other users
    @GetMapping("/searchUser")
    public void searchByName(@RequestParam(value = "query") String query)
    {
        userService.searchByName(query);
    }

    //For testing purposes
    @GetMapping("/purgeUsers")
    public void clearUsers()
    {
        userRepository.deleteAll();
    }
}