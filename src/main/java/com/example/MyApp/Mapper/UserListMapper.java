package com.example.MyApp.Mapper;

import com.example.MyApp.Dto.*;
import com.example.MyApp.Entity.UserList;
import com.example.MyApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserListMapper {
    @Autowired private static UserRepository userRepository;

    public static UserListDto map(UserList userList)
    {
        return UserListDto.builder()
                .list_name(userList.getList_name())
                .comic_id(userList.getComic_id())
                .username(userRepository.findById(userList.getUser_id()).getUsername())
                .build();
    }

    public static UserList convertEntity (UserListDto userListDto)
    {
        UserList userList = new UserList();
        userList.setList_name(userListDto.getList_name());
        userList.setComic_id(userListDto.getComic_id());
        userList.setUser_id(userRepository.findByUsername(userListDto.getUsername()).getUser_id());
        return userList;
    }
}
