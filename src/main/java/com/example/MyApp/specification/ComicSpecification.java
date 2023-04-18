package com.example.MyApp.specification;

import com.example.MyApp.entity.Comic;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class ComicSpecification implements Specification <Comic> {

    private final Comic filter;

    public ComicSpecification(Comic filter)
    {
        super();
        this.filter = filter;
    }


    @Override
    public Predicate toPredicate(Root<Comic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter != null){
            if(filter.getTitle() != null && !filter.getTitle().trim().equals(""))
            {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filter.getTitle() + "%"));
            }

            if(filter.getPublisher() != null && !filter.getPublisher().trim().equals(""))
            {
                predicates.add(criteriaBuilder.like(root.get("publisher"), "%" + filter.getPublisher() + "%"));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
