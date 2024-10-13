package com.artisanat_backend.controller;

import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.dto.response.ReviewResponseDto; // Import the response DTO
import com.artisanat_backend.mapper.ReviewMapper; // Import the ReviewMapper
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Review;
import com.artisanat_backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    /**
     * Récupère tous les avis.
     * @return Liste des avis.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN', 'CUSTOMER')")
    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    /**
     * Récupère tous les avis par ProductId.
     * @return Liste des avis.
     */
    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviewsByProductId(@PathVariable long productId) {
        List<Review> reviews = reviewService.getAllReviewsByProductId(productId);
        List<ReviewResponseDto> reviewDtos = reviews.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewDtos, HttpStatus.OK);
    }

    /**
     * Récupère un avis par son ID.
     * @param id ID de l'avis.
     * @return Avis demandé.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(reviewMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Ajoute un nouvel avis.
     * @param reviewDto Avis à ajouter.
     * @return Avis ajouté.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/add-review/{productId}")
    public ResponseEntity<ReviewResponseDto> addReview(@PathVariable Long productId,
                                                       @RequestBody ReviewRequestDto reviewDto,
                                                       @AuthenticationPrincipal Customer customer) {
        Review addedReview = reviewService.addReview(productId, reviewDto, customer);
        ReviewResponseDto reviewResponseDto = reviewMapper.toDto(addedReview);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.CREATED);
    }

    /**
     * Met à jour un avis existant.
     * @param id ID de l'avis à mettre à jour.
     * @param reviewDto Avis mis à jour.
     * @return Avis mis à jour.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewDto, @AuthenticationPrincipal Customer customer) {
        try {
            Review review = reviewService.updateReview(id, reviewDto, customer);
            ReviewResponseDto reviewResponseDto = reviewMapper.toDto(review);
            return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprime un avis par son ID.
     * @param id ID de l'avis à supprimer.
     * @return Réponse HTTP.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id, @AuthenticationPrincipal Customer customer) {
        reviewService.deleteReview(id, customer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
