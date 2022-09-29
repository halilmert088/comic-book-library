package com.example.MyApp.Controller;

import com.example.MyApp.Dto.ComicDto;
import com.example.MyApp.Dto.UserListDto;
import com.example.MyApp.Entity.Comic;
import com.example.MyApp.Entity.User;
import com.example.MyApp.Entity.UserList;
import com.example.MyApp.Mapper.ComicMapper;
import com.example.MyApp.Mapper.UserListMapper;
import com.example.MyApp.Repository.ComicRepository;
import com.example.MyApp.Repository.UserListRepository;
import com.example.MyApp.Service.ServiceImpl.ComicServiceImpl;
import com.example.MyApp.Service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/list")
public class UserListController {
    @Autowired private UserListService userListService;
    @Autowired private UserListRepository userListRepository;
    @Autowired private ComicServiceImpl comicService;

    public void addToList(int listId, Comic comic)
    {
        UserList userList = new UserList();
        userList = userListRepository.findById(listId);//Get list

        if (!userList.getComic_id().contains(comic.getComic_id()))//To have unique values in the list
            userList.getComic_id().add(comic.getComic_id());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comic already in list!");

        userList = null; //memory management :P
    }

    public void createList(String name)
    {
        UserList userList = new UserList();
        userList.setList_name(name);
        //userList.setUser_id(token'dan alınacak);
        userList.setComic_id(null);

        userListRepository.save(userList);
        userList = null;
    }

    @PostMapping(path = "/newList", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserList> newList(@RequestBody UserListDto userListDto)
    {
        UserList entity = userListRepository.save(UserListMapper.convertEntity(userListDto));
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<ComicDto> displayList (@RequestParam(value = "id") int id)
    {
        UserList query = userListRepository.findById(id);
        List<ComicDto> list = new ArrayList<ComicDto>();

        for(int i=0; i < query.getComic_id().size(); i++)
        {
            list.add(ComicMapper.map(comicService.findById(query.getComic_id().get(i))));
        }
        return list;
    }
}