package com.example.MyApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ComicDTO {
    private String title;
    private String publisher;
    private String release_date;
}
