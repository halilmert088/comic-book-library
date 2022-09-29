package com.example.MyApp.Dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class UserListDto {
    private String list_name;
    private ArrayList<Integer> comic_id;
    private String username; //need to find a way to get this from the generated token

    public UserListDto(){}

    public UserListDto(String list_name, ArrayList<Integer> comic_id, String username)
    {
        this.list_name = list_name;
        this.comic_id = comic_id;
        this.username = username;
    }
}
