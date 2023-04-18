package com.example.MyApp.service.serviceImpl;

import com.example.MyApp.dto.UserDTO;
import com.example.MyApp.entity.User;
import com.example.MyApp.repository.UserRepository;
import com.example.MyApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public List<UserDTO> searchByName(String query)
    {
        var users = (List<User>) userRepository.findAll();
        List<UserDTO> userDto = new ArrayList<UserDTO>();

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUsername().contains(query))
            {
                userDto.add(UserDTO.builder().
                        username(users.get(i).getUsername())
                        .build());
            }
        }

        return userDto;
    }
}
