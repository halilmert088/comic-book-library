package com.example.MyApp.Dto;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserDto {
    private String email;
    private String username;
    private String password;

    public UserDto(){}

    public UserDto(String email, String username, String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
