package com.example.MyApp.Service;

import com.example.MyApp.Dto.UserDto;
import com.example.MyApp.Entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    User loadUserByUsername (String username) throws UsernameNotFoundException;
}
