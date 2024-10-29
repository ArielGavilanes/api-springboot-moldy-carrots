package com.proyecto.moldy_carrots.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.reviews.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
