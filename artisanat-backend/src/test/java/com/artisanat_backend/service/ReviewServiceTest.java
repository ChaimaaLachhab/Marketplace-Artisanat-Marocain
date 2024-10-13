package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.mapper.ReviewMapper;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.model.Review;
import com.artisanat_backend.repository.ProductRepository;
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

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private ReviewRequestDto reviewRequestDto;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        review = new Review();
        review.setId(1L);
        review.setRating(4);
        review.setComment("Good product!");
        review.setProduct(product);

        reviewRequestDto = new ReviewRequestDto();
        reviewRequestDto.setRating(4);
        reviewRequestDto.setComment("Good product!");

        customer = new Customer();
        customer.setId(1L);

        product = new Product();
        product.setId(1L);
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
    void addReview_SavesAndReturnsReview() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(reviewMapper.toEntity(reviewRequestDto)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(review);

        Review result = reviewService.addReview(productId, reviewRequestDto, customer);

        assertNotNull(result);
        assertEquals(review, result);
        verify(productRepository, times(1)).findById(productId);
        verify(reviewMapper, times(1)).toEntity(reviewRequestDto);
        verify(reviewRepository, times(1)).save(review);
        verify(productService, times(1)).updateProductRating(productId);
    }

    @Test
    void deleteReview_ThrowsException_WhenCustomerMismatch() {
        Long reviewId = 1L;
        Customer otherCustomer = new Customer();
        otherCustomer.setId(2L);
        review.setCustomer(otherCustomer);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Exception exception = assertThrows(RuntimeException.class, () ->
                reviewService.deleteReview(reviewId, customer));

        assertEquals("Customer id mismatch", exception.getMessage());
        verify(reviewRepository, never()).deleteById(any());
    }
}
