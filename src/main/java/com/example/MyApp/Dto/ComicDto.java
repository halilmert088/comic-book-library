package com.example.MyApp.Dto;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ComicDto {
    private String title;
    private String publisher;
    private String release_date;

    public ComicDto(){}

    public ComicDto(String title, String publisher, String release_date)
    {
        this.title = title;
        this.publisher = publisher;
        this.release_date = release_date;
    }
}
