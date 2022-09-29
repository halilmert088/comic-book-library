package com.example.MyApp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(schema = "public", name = "list")
@Getter
@Setter
public class UserList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int list_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "list_name")
    private String list_name;

    @Column(name = "comic_id")
    private ArrayList<Integer> comic_id;
}
