package com.proyecto.moldy_carrots.media.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.media.model.Media;
import com.proyecto.moldy_carrots.media.repository.MediaRepository;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public Media createMedia(Media newMedia) throws BadRequestException {
        Media existingMedia = mediaRepository.findByName(newMedia.getName());
        if (existingMedia != null) {
            throw new BadRequestException("Media already registered");
        }

        return mediaRepository.save(newMedia);
    }

    public Media findByName(String name) {
        return mediaRepository.findByName(name);
    }
}
