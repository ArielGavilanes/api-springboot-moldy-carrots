package com.proyecto.moldy_carrots.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.moldy_carrots.media.model.Media;
import com.proyecto.moldy_carrots.media.service.MediaService;
import com.proyecto.moldy_carrots.reviews.dto.ReviewDTO;
import com.proyecto.moldy_carrots.reviews.model.Review;
import com.proyecto.moldy_carrots.reviews.service.ReviewService;
import com.proyecto.moldy_carrots.users.model.User;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MediaService mediaService;

    @GetMapping("byUserId")
    @ResponseBody
    public ResponseEntity<List<Review>> findReviewByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();

        List<Review> reviews = reviewService.findReviewByUserId(authenticatedUser.getUserId());

        return ResponseEntity.ok(reviews);
    };

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Review> createReview(@RequestBody ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User authenticatedUser = (User) authentication.getPrincipal();
        Media media = mediaService.findById(reviewDTO.getMediaId());

        Review review = new Review();
        review.setReview(reviewDTO.getReview());
        review.setScore(reviewDTO.getScore());
        review.setUserId(authenticatedUser);
        review.setMediaId(media);

        Review newReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);

    }

}
