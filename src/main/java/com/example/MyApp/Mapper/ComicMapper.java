package com.example.MyApp.Mapper;

import com.example.MyApp.Dto.*;
import com.example.MyApp.Entity.Comic;
import org.springframework.stereotype.Component;

@Component
public class ComicMapper {
    public static ComicDto map(Comic comic)
    {
        return ComicDto.builder()
                .title(comic.getTitle())
                .publisher(comic.getPublisher())
                .release_date(comic.getRelease_date())
                .build();
    }

    public static Comic convertEntity(ComicDto comicDto)
    {
        Comic comic = new Comic();
        comic.setTitle(comicDto.getTitle());
        comic.setPublisher(comicDto.getPublisher());
        comic.setRelease_date(comicDto.getRelease_date());
        return comic;
    }
}
