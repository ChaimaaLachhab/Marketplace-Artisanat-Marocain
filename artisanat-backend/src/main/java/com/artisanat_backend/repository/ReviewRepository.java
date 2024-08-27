package com.artisanat_backend.repository;

import com.artisanat_backend.entity.Review;
import com.artisanat_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
