package com.proyecto.moldy_carrots.genres_catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.genres_catalogue.repository.GenreCatalogueRepository;

@Service
public class GenresService {

    @Autowired
    private GenreCatalogueRepository genreCatalogueRepository;

    public GenreCatalogue getGenreById(long id) {
        return genreCatalogueRepository.findById(id).orElse(null);
    }

    public GenreCatalogue createGenre(GenreCatalogue newGenre) {
        return genreCatalogueRepository.save(newGenre);
    }
}
