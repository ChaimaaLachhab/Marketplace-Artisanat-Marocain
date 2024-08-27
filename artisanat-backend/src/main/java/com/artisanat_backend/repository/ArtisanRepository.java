package com.artisanat_backend.repository;

import com.artisanat_backend.entity.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
    // Add any custom queries if necessary
}
