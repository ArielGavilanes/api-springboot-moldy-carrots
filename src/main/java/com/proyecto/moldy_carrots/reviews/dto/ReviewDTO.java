package com.proyecto.moldy_carrots.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDTO {
    @Size(min = 10, max = 500, message = "Review must be between 10 and 500 characters.")
    private String review;

    @Min(value = 0, message = "Score must be at least 0.")
    @Max(value = 5, message = "Score must be at most 5.")
    private Integer score;

    private long mediaId;
}
