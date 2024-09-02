package com.artisanat_backend.controller;

import com.artisanat_backend.model.Review;
import com.artisanat_backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Récupère tous les avis.
     * @return Liste des avis.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    /**
     * Récupère un avis par son ID.
     * @param id ID de l'avis.
     * @return Avis demandé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Ajoute un nouvel avis.
     * @param review Avis à ajouter.
     * @return Avis ajouté.
     */
    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review addedReview = reviewService.addReview(review);
        return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
    }

    /**
     * Met à jour un avis existant.
     * @param id ID de l'avis à mettre à jour.
     * @param updatedReview Avis mis à jour.
     * @return Avis mis à jour.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        try {
            Review review = reviewService.updateReview(id, updatedReview);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprime un avis par son ID.
     * @param id ID de l'avis à supprimer.
     * @return Réponse HTTP.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
