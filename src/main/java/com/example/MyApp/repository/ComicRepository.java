package com.example.MyApp.repository;

import com.example.MyApp.entity.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer>, JpaSpecificationExecutor<Comic> {
    Comic findById (int id);
    List<Comic> findAllByPublisher(String publisher);
    Page<Comic> findAll(Pageable pageable);
    Page<Comic> findAll (Specification<Comic> specification, Pageable pageable);
}
