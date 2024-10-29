package com.proyecto.moldy_carrots.genres_catalogue.model;


import java.util.List;

import com.proyecto.moldy_carrots.genres.model.Genres;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genres catalogue")
public class GenreCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "genreId", cascade = CascadeType.ALL)
    private List<Genres> genres;
}
