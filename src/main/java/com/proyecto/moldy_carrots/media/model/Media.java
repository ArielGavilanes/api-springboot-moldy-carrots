package com.proyecto.moldy_carrots.media.model;

import java.time.LocalDate;
import java.util.List;

import com.proyecto.moldy_carrots.genres.model.Genres;
import com.proyecto.moldy_carrots.media_types_catalogue.model.MediaTypesCatalogue;
import com.proyecto.moldy_carrots.reviews.model.Review;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Lob
    @Column(name = "coverImage", nullable = false, columnDefinition = "bytea")
    private byte[] coverImage;

    @Size(min = 255, max = 500)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rating", nullable = false, columnDefinition = "float default 0.0")
    private Float rating;

    @Past
    @Column(name = "releaseDate", nullable = false)
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private MediaTypesCatalogue typeId; 

    @OneToMany(mappedBy = "mediaId", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "mediaId", cascade = CascadeType.ALL)
    private List<Genres> genres;
}
