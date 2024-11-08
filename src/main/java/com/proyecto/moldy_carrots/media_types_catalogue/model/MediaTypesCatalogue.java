package com.proyecto.moldy_carrots.media_types_catalogue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}
