package com.artisanat_backend.repository;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
    List<Artisan> findByVerificationStatus(VerificationStatus verificationStatus);
}
