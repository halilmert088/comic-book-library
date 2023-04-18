package com.example.MyApp.mapper;

import com.example.MyApp.dto.*;
import com.example.MyApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO map(User user)
    {
        return UserDTO.builder().
                email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User convertEntity (UserDTO userDto)
    {
        User user = new User();
        user.setEmail(user.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
