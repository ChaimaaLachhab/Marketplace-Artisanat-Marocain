package com.artisanat_backend.service;

import com.artisanat_backend.model.Review;
import com.artisanat_backend.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        review = new Review();
        review.setId(1L);
        review.setRating(4);
        review.setComment("Good product!");
    }

    @Test
    void getAllReviews_ReturnsListOfReviews() {
        List<Review> reviews = new ArrayList<>();
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertEquals(reviews, result);
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void getReviewById_ReturnsReview() {
        Long reviewId = 1L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Optional<Review> result = reviewService.getReviewById(reviewId);

        assertTrue(result.isPresent());
        assertEquals(review, result.get());
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void getReviewById_ReturnsEmpty_WhenReviewNotFound() {
        Long reviewId = 1L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        Optional<Review> result = reviewService.getReviewById(reviewId);

        assertFalse(result.isPresent());
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void addReview_SavesAndReturnsReview() {
        when(reviewRepository.save(review)).thenReturn(review);

        Review result = reviewService.addReview(review);

        assertNotNull(result);
        assertEquals(review, result);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void updateReview_UpdatesAndReturnsReview_WhenReviewExists() {
        Long reviewId = 1L;
        Review updatedReview = new Review();
        updatedReview.setRating(5);
        updatedReview.setComment("Excellent product!");

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(review);

        Review result = reviewService.updateReview(reviewId, updatedReview);

        assertNotNull(result);
        assertEquals(5, result.getRating());
        assertEquals("Excellent product!", result.getComment());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    void updateReview_ThrowsException_WhenReviewNotFound() {
        Long reviewId = 1L;
        Review updatedReview = new Review();
        updatedReview.setRating(5);
        updatedReview.setComment("Excellent product!");

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () ->
                reviewService.updateReview(reviewId, updatedReview)
        );

        assertEquals("Review not found", exception.getMessage());
        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, never()).save(any());
    }

    @Test
    void deleteReview_DeletesReview() {
        Long reviewId = 1L;

        reviewService.deleteReview(reviewId);

        verify(reviewRepository, times(1)).deleteById(reviewId);
    }
}
