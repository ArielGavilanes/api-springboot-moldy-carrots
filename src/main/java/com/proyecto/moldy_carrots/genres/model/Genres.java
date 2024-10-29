package com.proyecto.moldy_carrots.genres.model;

import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.media.model.Media;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mediaGenreId;

    @ManyToOne
    @JoinColumn(name = "mediaId")
    private Media mediaId;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private GenreCatalogue genreId; 

}
