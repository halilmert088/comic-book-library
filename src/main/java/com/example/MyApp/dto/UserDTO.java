package com.example.MyApp.dto;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserDTO {
    private String email;
    private String username;
    private String password;

    public UserDTO(){}

    public UserDTO(String email, String username, String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
