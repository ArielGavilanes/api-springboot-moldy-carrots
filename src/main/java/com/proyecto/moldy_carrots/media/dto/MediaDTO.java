package com.proyecto.moldy_carrots.media.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MediaDTO {

    @NotNull(message = "The media ID cannot be null")
    private long mediaId;

    @NotNull(message = "The name cannot be null")
    @Size(min = 5, message = "The name must have at least 5 characters")
    private String name;

    @NotNull(message = "The cover image cannot be null")
    private MultipartFile coverImage;

    @NotNull(message = "The description cannot be null")
    @Size(min = 100, max = 500, message = "The description must be between 10 and 500 characters")
    private String description;

    private Float rating;

    @NotNull(message = "The release date cannot be null")
    @Past(message = "The release date must be in the past")
    private LocalDate releaseDate;

    @NotNull(message = "The media type cannot be null")
    private Long typeId;

    @NotNull(message = "The genre type cannot be null")
    private Long genreId;

}
