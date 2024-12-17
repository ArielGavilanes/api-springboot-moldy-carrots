package com.proyecto.moldy_carrots.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.media.model.Media;
import com.proyecto.moldy_carrots.media.service.MediaService;
import com.proyecto.moldy_carrots.reviews.dto.ReviewDTO;
import com.proyecto.moldy_carrots.reviews.model.Review;
import com.proyecto.moldy_carrots.reviews.service.ReviewService;
import com.proyecto.moldy_carrots.users.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MediaService mediaService;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<Review>> findAllReviews() {
        List<Review> allReviews = reviewService.findAllReviews();
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/{reviewId}")
    @ResponseBody
    public ResponseEntity<Review> findReviewById(@PathVariable long reviewId) {
        Review review = reviewService.findReviewById(reviewId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("byUserId")
    @ResponseBody
    public ResponseEntity<List<Review>> findReviewByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();

        List<Review> reviews = reviewService.findReviewByUserId(authenticatedUser.getUserId());

        return ResponseEntity.ok(reviews);
    };

    @GetMapping("byMediaId/{mediaId}")
    @ResponseBody
    public ResponseEntity<List<Review>> findReviewByMediaId(@PathVariable long mediaId) {

        List<Review> reviews = reviewService.findReviewByMediaId(mediaId);

        return ResponseEntity.ok(reviews);
    };

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();
        Media media = mediaService.findById(reviewDTO.getMediaId());

        Review review = new Review();
        review.setReview(reviewDTO.getReview());
        review.setScore(reviewDTO.getScore());
        review.setUserId(authenticatedUser);
        review.setMediaId(media);

        Review newReview = reviewService.createReview(review);

        mediaService.updateRating(reviewDTO.getMediaId());
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);

    }

    @PutMapping("/{reviewId}")
    @ResponseBody
    public ResponseEntity<Review> updateReview(@PathVariable long reviewId, @RequestBody ReviewDTO review)
            throws BadRequestException {

        Review updatedReview = reviewService.updateReview(reviewId, review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
