package com.example.MyApp.Service;

import com.example.MyApp.Entity.Comic;

import java.util.List;

public interface ComicService {
    List<Comic> findAll();
    Comic findById(int id);
}
