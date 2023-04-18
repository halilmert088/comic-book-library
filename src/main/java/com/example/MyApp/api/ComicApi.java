package com.example.MyApp.api;

import com.example.MyApp.entity.Comic;
import com.example.MyApp.service.serviceImpl.ComicServiceImpl;
import com.example.MyApp.specification.ComicSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/comicApi")
public class ComicApi {
    private final ComicServiceImpl comicService;

    @GetMapping("/pageAll")
    public Page<Comic> pageAll (Pageable pageable)
    {
        return comicService.findAll(pageable);
    }

    @GetMapping("/page")
    public Page<Comic> page (@RequestBody Comic filter, Pageable pageable)
    {
        Specification<Comic> spec = new ComicSpecification(filter);
        return comicService.findAll(spec, pageable);
    }
}
