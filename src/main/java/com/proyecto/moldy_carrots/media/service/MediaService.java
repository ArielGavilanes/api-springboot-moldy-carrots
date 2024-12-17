package com.proyecto.moldy_carrots.media.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.moldy_carrots.exception.BadRequestException;
import com.proyecto.moldy_carrots.media.model.Media;
import com.proyecto.moldy_carrots.media.repository.MediaRepository;
import com.proyecto.moldy_carrots.reviews.model.Review;
import com.proyecto.moldy_carrots.reviews.service.ReviewService;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ReviewService reviewService;

    public List<Media> findAllMedia() {
        return mediaRepository.findAll();
    }

    public Media createMedia(Media newMedia) throws BadRequestException {
        Media existingMedia = mediaRepository.findByName(newMedia.getName());

        if (existingMedia != null) {
            throw new BadRequestException("Media already registered");
        }

        return mediaRepository.save(newMedia);
    }

    public Media findByName(String name) {
        return mediaRepository.findByName(name);
    }

    public List<Media> searchMedia(String name) {
        return mediaRepository.findByNameContaining(name);
    }

    public List<Media> findByReleaseDateBetween() {
        LocalDate today = LocalDate.now();
        LocalDate threeDatsBefore = today.minusDays(7);
        return mediaRepository.findByReleaseDateBetween(threeDatsBefore, today);
    }

    public Media findById(long mediaId) {
        return mediaRepository.findById(mediaId).orElse(null);
    }

    public void updateRating(long mediaId) {

        List<Review> reviews = reviewService.findAllReviews();
        int totalRating = reviews.size() + 1;
        int acumulativeRating = 0;
        for (Review review : reviews) {
            acumulativeRating += review.getScore();
        }

        float finalRating = (float) acumulativeRating / totalRating;
        Media media = findById(mediaId);

        media.setRating(finalRating);
        mediaRepository.save(media);
    }

    public Media updateMedia(Media mediaDetails) throws BadRequestException {
        return mediaRepository.save(mediaDetails);
    }

    public void deleteMedia(long mediaId) {
        Media media = findById(mediaId);
        mediaRepository.delete(media);
    }
}
