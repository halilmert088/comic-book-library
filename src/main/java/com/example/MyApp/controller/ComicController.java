package com.example.MyApp.controller;

import com.example.MyApp.repository.ComicRepository;
import com.example.MyApp.webScraper.Scrape;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/comic")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComicController {
    private final ComicRepository comicRepository;
    private final Scrape scrape;

    //Functions for debug/testing
    @GetMapping("/purge")
    public void purgeDb() throws IOException {
        scrape.purgeDatabase();
        assert (comicRepository.count()) == 0;
    }

    @GetMapping("/update")
    public void manualUpdate() throws IOException {
        if(comicRepository.count() == 0)
            scrape.updateDatabase();
    }
}