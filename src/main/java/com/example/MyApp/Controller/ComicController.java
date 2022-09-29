package com.example.MyApp.Controller;

import com.example.MyApp.Dto.ComicDto;
import com.example.MyApp.Entity.Comic;
import com.example.MyApp.Repository.ComicRepository;
import com.example.MyApp.Service.ComicService;
import com.example.MyApp.Service.ServiceImpl.ComicServiceImpl;

import com.example.MyApp.WebScraper.Scrape;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ComicController {
    @Autowired private ComicRepository comicRepository;
    @Autowired private ComicServiceImpl comicService;

    //Basic search function
    @GetMapping("/searchComic")
    public List<ComicDto> search(@RequestParam(value = "name") String name)
    {
        return comicService.search(name);
    }

    @GetMapping("/publisher")
    public List<ComicDto> publisher(@RequestParam(value = "name") String name)
    {
        return comicService.publisher(name);
    }


    //Functions for debug/testing
    @GetMapping("/purge")
    public void purgeDb() throws IOException {
        Scrape scrape = new Scrape();
        scrape.purgeDatabase(comicRepository);
        scrape = null;
    }

    @GetMapping("/update")
    public void manualUpdate() throws IOException
    {
        Scrape scrape = new Scrape();

        if (comicRepository.count() == 0)
            scrape.updateDatabase(comicRepository);

        scrape = null;
    }
}