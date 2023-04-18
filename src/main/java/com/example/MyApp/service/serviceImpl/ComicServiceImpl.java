package com.example.MyApp.service.serviceImpl;

import com.example.MyApp.dto.ComicDTO;
import com.example.MyApp.entity.Comic;
import com.example.MyApp.mapper.ComicMapper;
import com.example.MyApp.repository.ComicRepository;
import com.example.MyApp.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComicServiceImpl implements ComicService {
    private final ComicRepository comicRepository;
    private final ComicMapper mapper;

    public static List<String> publishers = new ArrayList<String>();

    @Override
    public List<Comic> findAll()
    {
        return comicRepository.findAll();
    }

    @Override
    public Comic findById(int id)
    {
        return comicRepository.findById(id);
    }

    @Override
    public List<Comic> findAllByPublisher (String publisher)
    {
        return comicRepository.findAllByPublisher(publisher);
    }
    @Override
    public Page<Comic> findAll(Pageable pageable)
    {
        return comicRepository.findAll(pageable);
    }

    @Override
    public Page<Comic> findAll(Specification<Comic> specification, Pageable pageable)
    {
        return comicRepository.findAll(specification, pageable);
    }
}
