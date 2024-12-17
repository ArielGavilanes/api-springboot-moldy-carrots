package com.proyecto.moldy_carrots.genres_catalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.genres_catalogue.service.GenresService;

@RestController
@RequestMapping("/api/genre")
public class GenresController {
    @Autowired
    private GenresService genresService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<GenreCatalogue>> findAllGenres() {
        List<GenreCatalogue> genres = genresService.findAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<GenreCatalogue> findGenreById(@PathVariable long id) {
        GenreCatalogue genre = genresService.findGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<GenreCatalogue> createUser(@RequestBody GenreCatalogue genre) {
        GenreCatalogue createdGenre = genresService.createGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @PutMapping("/{genreId}")
    @ResponseBody
    public ResponseEntity<GenreCatalogue> updateGenre(@PathVariable long genreId,
            @RequestBody GenreCatalogue genreDetails) {
        GenreCatalogue updatedGenre = genresService.updateGenre(genreId, genreDetails);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{genreId}")
    public ResponseEntity<Void> deleteReview(@PathVariable long genreId) {
        genresService.deleteGenre(genreId);
        return ResponseEntity.noContent().build();
    }

}
