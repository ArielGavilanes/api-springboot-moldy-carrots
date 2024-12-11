package com.proyecto.moldy_carrots.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.reviews.model.Review;
import com.proyecto.moldy_carrots.reviews.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findReviewByUserId(long userId) {
        return reviewRepository.findByUserId_UserId(userId);
    }

    public Review createReview(Review newReview) {
        return reviewRepository.save(newReview);
    }
}
