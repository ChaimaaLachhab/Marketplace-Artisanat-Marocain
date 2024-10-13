package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.mapper.ReviewMapper;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.model.Review;
import com.artisanat_backend.repository.CustomerRepository;
import com.artisanat_backend.repository.ProductRepository;
import com.artisanat_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper reviewMapper;
    private final ProductService productService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, ReviewMapper reviewMapper, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.reviewMapper = reviewMapper;
        this.productService = productService;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getAllReviewsByProductId(Long productId) {
        return reviewRepository.findReviewsByProductId(productId);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review addReview(Long productId, ReviewRequestDto reviewDto, Customer customer) {
        Product product = productRepository.findById(productId).get();
        Review review = reviewMapper.toEntity(reviewDto);
        review.setProduct(product);
        review.setCustomer(customer);
        productService.updateProductRating(review.getProduct().getId());
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewRequestDto reviewDto, Customer customer) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        if(review.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("Customer id mismatch");
        }
        reviewMapper.partialUpdate(reviewDto, review);
        productService.updateProductRating(review.getProduct().getId());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id, Customer customer) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        if(review.getCustomer().getId().equals(customer.getId())) {
            reviewRepository.deleteById(id);
        }
        throw new RuntimeException("Customer id mismatch");
    }
}
