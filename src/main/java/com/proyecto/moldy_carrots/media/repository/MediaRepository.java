package com.proyecto.moldy_carrots.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.media.model.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByName(String name);
}
