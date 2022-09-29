package com.example.MyApp.Service.ServiceImpl;

import com.example.MyApp.Entity.User;
import com.example.MyApp.Repository.UserRepository;
import com.example.MyApp.Security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    public UserDetailsServiceImpl (UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(int id)
    {
        User user = userRepository.findById(id);
        return JwtUserDetails.create(user);
    }
}