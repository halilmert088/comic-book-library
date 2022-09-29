package com.example.MyApp.Service.ServiceImpl;

import com.example.MyApp.Dto.ComicDto;
import com.example.MyApp.Entity.Comic;
import com.example.MyApp.Repository.ComicRepository;
import com.example.MyApp.Service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl implements ComicService {
    @Autowired private ComicRepository comicRepository;

    public List<String> publishers = new ArrayList<String>();

    @Override
    public List<Comic> findAll()
    {
        var comics = (List<Comic>) comicRepository.findAll();
        return comics;
    }

    @Override
    public Comic findById(int id)
    {
        var  comic = (Comic) comicRepository.findById(id);
        return comic;
    }

    public List<ComicDto> search(String name)
    {
        var comics = (List<Comic>) findAll();
        var results = (List<ComicDto>) new ArrayList<ComicDto>();

        for(int i = 0; i < comics.size(); i++)
        {
            if(comics.get(i).getTitle().contains(name))
            {
                results.add(ComicDto.builder()
                        .title(comics.get(i).getTitle())
                        .publisher(comics.get(i).getPublisher())
                        .release_date(comics.get(i).getRelease_date())
                        .build());
            }
        }

        //Throws not found exception if the search parameter is not found on the list
        if (results.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comic not found!");
        }

        return results;
    }

    public List<ComicDto> publisher(String name)
    {
        var comics = (List<Comic>) findAll();
        var results = (List<ComicDto>) new ArrayList<ComicDto>();

        for(int i = 0; i < comics.size(); i++)
        {
            if(comics.get(i).getPublisher().contains(name))
            {
                results.add(ComicDto.builder()
                        .title(comics.get(i).getTitle())
                        .publisher(comics.get(i).getPublisher())
                        .release_date(comics.get(i).getRelease_date())
                        .build());
            }
        }

        //Throws not found exception if the search parameter is not found on the list
        if (results.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comic not found!");
        }

        return results;
    }
}
