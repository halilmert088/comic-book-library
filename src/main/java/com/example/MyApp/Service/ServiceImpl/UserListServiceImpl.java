package com.example.MyApp.Service.ServiceImpl;

import com.example.MyApp.Entity.UserList;
import com.example.MyApp.Repository.UserListRepository;
import com.example.MyApp.Service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {
    @Autowired private UserListRepository userListRepository;

    @Override
    public UserList findById(int id)
    {
        var users = (UserList) userListRepository.findById(id);
        return users;
    }
}