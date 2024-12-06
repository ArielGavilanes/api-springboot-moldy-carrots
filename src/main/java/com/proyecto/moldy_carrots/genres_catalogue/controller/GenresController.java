package com.proyecto.moldy_carrots.genres_catalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<GenreCatalogue> createUser(@RequestBody GenreCatalogue genre) {
        GenreCatalogue createdGenre = genresService.createGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

}
