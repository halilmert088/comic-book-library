package com.example.MyApp.Controller;

import com.example.MyApp.Dto.UserDto;
import com.example.MyApp.Entity.User;
import com.example.MyApp.Mapper.UserMapper;
import com.example.MyApp.Repository.UserRepository;
import com.example.MyApp.Security.JwtAuthenticationEntryPoint;
import com.example.MyApp.Security.JwtTokenProvider;
import com.example.MyApp.Security.JwtUserDetails;
import com.example.MyApp.Service.ServiceImpl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private UserServiceImpl userService;

    private AuthenticationManager authenticationManager = new AuthenticationManager() {
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return authentication;
        }
    };
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @PostMapping("/login")
    public String login(@RequestBody UserDto login)
    {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer " + jwtToken;
    }

    //Search other users
    @GetMapping("/searchUser")
    public void searchByName(@RequestParam(value = "query") String query)
    {
        userService.searchByName(query);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void register(@RequestBody @NotNull UserDto userDto)
    {
        userService.register(userDto);
    }

    //For testing purposes
    @GetMapping("/purgeUsers")
    public void clearUsers()
    {
        userRepository.deleteAll();
    }
}