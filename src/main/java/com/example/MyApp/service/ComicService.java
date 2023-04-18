package com.example.MyApp.service;

import com.example.MyApp.entity.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ComicService {
    List<Comic> findAll();
    Comic findById(int id);
    List<Comic> findAllByPublisher(String publisher);
    Page<Comic> findAll(Pageable pageable);
    Page<Comic> findAll (Specification<Comic> specification, Pageable pageable);
}
