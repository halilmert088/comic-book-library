package com.example.MyApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "public", name = "comic")
@Data
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int comic_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "release_date", nullable = false)
    private String release_date;

    @Column(name = "image_url", nullable = false)
    private String image_url;
}