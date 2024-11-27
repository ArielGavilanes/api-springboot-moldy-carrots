package com.proyecto.moldy_carrots.media.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.genres_catalogue.service.GenresService;
import com.proyecto.moldy_carrots.media.dto.MediaDTO;
import com.proyecto.moldy_carrots.media.model.Media;
import com.proyecto.moldy_carrots.media.service.MediaService;
import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;
import com.proyecto.moldy_carrots.media_types_catalogue.service.MediaTypesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private MediaTypesService mediaTypesService;

    @Autowired
    private GenresService genresService;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Media> createMedia(@Valid @ModelAttribute MediaDTO mediaDTO,
            @RequestParam("coverImage") MultipartFile coverImage) throws IOException, BadRequestException {

        MediaTypesCatalogue mediaType = mediaTypesService.getMediaTypeById(mediaDTO.getTypeId());

        GenreCatalogue genre = genresService.getGenreById(mediaDTO.getGenreId());

        Media media = new Media();
        media.setName(mediaDTO.getName());
        media.setCoverImage(coverImage.getBytes());
        media.setDescription(mediaDTO.getDescription());
        media.setReleaseDate(mediaDTO.getReleaseDate());
        media.setTypeId(mediaType);
        media.setGenreId(genre);
        media.setRating(0.0f);

        Media newMedia = mediaService.createMedia(media);

        return ResponseEntity.status(HttpStatus.CREATED).body(newMedia);

    }
}
