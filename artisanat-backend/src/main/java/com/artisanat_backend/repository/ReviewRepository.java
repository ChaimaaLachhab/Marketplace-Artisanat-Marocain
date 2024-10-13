package com.artisanat_backend.repository;

import com.artisanat_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewsByProductId(Long id);
}
