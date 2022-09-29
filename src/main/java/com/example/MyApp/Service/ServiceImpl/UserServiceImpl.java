package com.example.MyApp.Service.ServiceImpl;

import com.example.MyApp.Dto.UserDto;
import com.example.MyApp.Entity.User;
import com.example.MyApp.Repository.UserRepository;
import com.example.MyApp.Service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepository;

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

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return existsByUsername(username);
    }
    public ResponseEntity<User> register(UserDto userDto)
    {
        if(userRepository.existsByEmail(userDto.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "zattiri zort zort");

        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();//To encode and the password for added security

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public List<UserDto> searchByName(String query)
    {
        var users = (List<User>) userRepository.findAll();
        List<UserDto> userDto = new ArrayList<UserDto>();

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUsername().contains(query))
            {
                userDto.add(UserDto.builder().
                        username(users.get(i).getUsername())
                        .build());
            }
        }

        return userDto;
    }
}
