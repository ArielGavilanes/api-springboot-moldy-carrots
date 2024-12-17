package com.proyecto.moldy_carrots.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.reviews.dto.ReviewDTO;
import com.proyecto.moldy_carrots.reviews.model.Review;
import com.proyecto.moldy_carrots.reviews.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> findReviewByUserId(long userId) {
        return reviewRepository.findByUserId_UserId(userId);
    }

    public List<Review> findReviewByMediaId(long mediaId) {
        return reviewRepository.findByMediaId_MediaId(mediaId);
    }

    public Review createReview(Review newReview) {
        return reviewRepository.save(newReview);
    }

    public Review findReviewById(long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public Review updateReview(long reviewId, ReviewDTO reviewDetails) throws BadRequestException {
        Review review = findReviewById(reviewId);
        if (review == null) {
            throw new BadRequestException("Review not found");
        }
        review.setReview(reviewDetails.getReview());
        review.setScore(reviewDetails.getScore());
        return reviewRepository.save(review);
    }

    public void deleteReview(long reviewId) {
        Review review = findReviewById(reviewId);
        reviewRepository.delete(review);
    }
}
