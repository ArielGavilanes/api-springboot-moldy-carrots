package com.proyecto.moldy_carrots.media.model;

import java.sql.Types;
import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;

import com.proyecto.moldy_carrots.genres_catalogue.model.GenreCatalogue;
import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mediaId;

    @Size(min = 5)
    @Column(name = "name", nullable = false)
    private String name;

    @JdbcTypeCode(Types.VARBINARY)
    @Column(name = "coverImage", nullable = false, columnDefinition = "bytea")
    private byte[] coverImage;

    @Size(min = 100, max = 500)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating", nullable = false, columnDefinition = "float")
    private Float rating;

    @Past
    @Column(name = "releaseDate", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private MediaTypesCatalogue typeId;

    @ManyToOne
    @JoinColumn(name = "genreId")
    private GenreCatalogue genreId;

}
