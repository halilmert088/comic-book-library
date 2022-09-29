package com.example.MyApp.Entity;

import com.example.MyApp.Dto.ComicDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "public", name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

//    @OneToMany(mappedBy = "list_id")
//    @JoinColumn(name = "list_id", nullable = false)
//    private List<Integer> list_id = new ArrayList<Integer>();
}
