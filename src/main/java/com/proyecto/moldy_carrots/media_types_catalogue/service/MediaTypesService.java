package com.proyecto.moldy_carrots.media_types_catalogue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;
import com.proyecto.moldy_carrots.media_types_catalogue.repository.MediaTypesCatalogueRepository;

@Service
public class MediaTypesService {
    @Autowired
    private MediaTypesCatalogueRepository mediaTypesCatalogueRepository;

    public MediaTypesCatalogue getMediaTypeById(long id) {
        return mediaTypesCatalogueRepository.findById(id).orElse(null);
    }

    public MediaTypesCatalogue createType(MediaTypesCatalogue newType) {
        return mediaTypesCatalogueRepository.save(newType);
    }
}
