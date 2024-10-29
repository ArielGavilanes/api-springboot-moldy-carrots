package com.proyecto.moldy_carrots.media_types_catalogue.model;


import java.util.List;

import com.proyecto.moldy_carrots.media.model.Media;

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
@Table(name = "media types catalogue")
public class MediaTypesCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL)
    private List<Media> medias;
}
