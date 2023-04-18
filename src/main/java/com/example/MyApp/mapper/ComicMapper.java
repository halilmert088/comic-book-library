package com.example.MyApp.mapper;

import com.example.MyApp.dto.ComicDTO;
import com.example.MyApp.entity.Comic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComicMapper {
    ComicDTO entityToDto (Comic comic);
    Comic dtoToEntity (ComicDTO comicDTO);

    List<ComicDTO> entitiesToDtos (List<Comic> comics);
    List<Comic> dtosToEntities (List<ComicDTO> comicDTOS);
}
