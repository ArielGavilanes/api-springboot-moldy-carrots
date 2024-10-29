package com.proyecto.moldy_carrots.genres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.genres.model.Genres;

public interface GenresRepository extends JpaRepository<Genres, Long>{

}
