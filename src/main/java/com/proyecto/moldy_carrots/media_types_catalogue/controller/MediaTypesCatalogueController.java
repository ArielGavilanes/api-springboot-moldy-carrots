package com.proyecto.moldy_carrots.media_types_catalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;
import com.proyecto.moldy_carrots.media_types_catalogue.service.MediaTypesService;

@RestController
@RequestMapping("/api/type")
public class MediaTypesCatalogueController {

    @Autowired
    private MediaTypesService mediaTypesService;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<MediaTypesCatalogue> createType(@RequestBody MediaTypesCatalogue type) {
        MediaTypesCatalogue newType = mediaTypesService.createType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(newType);
    }

}
