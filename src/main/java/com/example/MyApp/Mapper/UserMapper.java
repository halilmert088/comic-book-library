package com.example.MyApp.Mapper;

import com.example.MyApp.Dto.*;
import com.example.MyApp.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDto map(User user)
    {
        return UserDto.builder().
                email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public static User convertEntity (UserDto userDto)
    {
        User user = new User();
        user.setEmail(user.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
