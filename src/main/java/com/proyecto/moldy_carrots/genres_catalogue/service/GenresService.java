package com.proyecto.moldy_carrots.genres_catalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.genres_catalogue.repository.GenreCatalogueRepository;

@Service
public class GenresService {

    @Autowired
    private GenreCatalogueRepository genreCatalogueRepository;

    public GenreCatalogue findGenreById(long id) {
        return genreCatalogueRepository.findById(id).orElse(null);
    }

    public List<GenreCatalogue> findAllGenres() {
        return genreCatalogueRepository.findAll();
    }

    public GenreCatalogue createGenre(GenreCatalogue newGenre) {
        return genreCatalogueRepository.save(newGenre);
    }

    public GenreCatalogue updateGenre(long genreId, GenreCatalogue genreDetails) {
        GenreCatalogue genre = findGenreById(genreId);
        genre.setName(genreDetails.getName());
        return genreCatalogueRepository.save(genre);
    }

    public void deleteGenre(long genreId) {
        GenreCatalogue genre = findGenreById(genreId);
        genreCatalogueRepository.delete(genre);
    }

}
