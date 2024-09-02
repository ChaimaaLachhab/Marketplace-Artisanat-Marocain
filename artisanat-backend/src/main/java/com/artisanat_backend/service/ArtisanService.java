package com.artisanat_backend.service;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.model.SubOrder;
import com.artisanat_backend.repository.ArtisanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtisanService {

    @Autowired
    private ArtisanRepository artisanRepository;

    public List<Artisan> getAllArtisans() {
        return artisanRepository.findAll();
    }

    public Artisan getArtisanById(Long id) {
        return artisanRepository.findById(id).orElse(null);
    }

    public void deleteArtisan(Long id) {
        artisanRepository.deleteById(id);
    }

    public Artisan updateArtisan(Artisan artisan) {
        return artisanRepository.save(artisan);
    }

    public List<Artisan> getPendingArtisans() {
        return artisanRepository.findByVerificationStatus(VerificationStatus.PENDING);
    }

    public Artisan verifyArtisan(Long artisanId, VerificationStatus status) {
        Artisan artisan = artisanRepository.findById(artisanId)
                .orElseThrow(() -> new EntityNotFoundException("Artisan not found"));

        artisan.setVerificationStatus(status);
        return artisanRepository.save(artisan);
    }
}
