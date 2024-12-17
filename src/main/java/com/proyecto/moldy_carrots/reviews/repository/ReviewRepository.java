package com.proyecto.moldy_carrots.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.moldy_carrots.reviews.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId_UserId(Long userId);

    List<Review> findByMediaId_MediaId(Long userId);
}
