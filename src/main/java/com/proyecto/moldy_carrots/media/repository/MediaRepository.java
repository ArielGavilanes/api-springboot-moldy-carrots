package com.proyecto.moldy_carrots.media.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.media.model.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByName(String name);

    List<Media> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    List<Media> findByNameContaining(String name);

}
