package com.proyecto.moldy_carrots.media_types_catalogue.controller;

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

import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;
import com.proyecto.moldy_carrots.media_types_catalogue.service.MediaTypesService;

@RestController
@RequestMapping("/api/type")
public class MediaTypesCatalogueController {

    @Autowired
    private MediaTypesService mediaTypesService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<MediaTypesCatalogue>> findAllMediaTypes() {
        List<MediaTypesCatalogue> mediaTypes = mediaTypesService.findAllMediaTypes();
        return ResponseEntity.ok(mediaTypes);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MediaTypesCatalogue> getMediaTypeById(@PathVariable long id) {
        MediaTypesCatalogue mediaType = mediaTypesService.getMediaTypeById(id);
        return ResponseEntity.ok(mediaType);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<MediaTypesCatalogue> createType(@RequestBody MediaTypesCatalogue mediaType) {
        MediaTypesCatalogue createdMediaType = mediaTypesService.createType(mediaType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMediaType);
    }

    @PutMapping("/{typeId}")
    @ResponseBody
    public ResponseEntity<MediaTypesCatalogue> updateMediaType(@PathVariable long typeId,
            @RequestBody MediaTypesCatalogue mediaDetails) {
        MediaTypesCatalogue updatedMediaType = mediaTypesService.updateMediaType(typeId, mediaDetails);
        return ResponseEntity.ok(updatedMediaType);
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<Void> deleteMediaType(@PathVariable long typeId) {
        mediaTypesService.deleteMediaType(typeId);
        return ResponseEntity.noContent().build();
    }

}
