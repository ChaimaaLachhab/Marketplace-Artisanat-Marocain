package com.artisanat_backend.service;

import com.artisanat_backend.entity.Artisan;
import com.artisanat_backend.repository.ArtisanRepository;
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

    public Artisan createArtisan(Artisan artisan) {
        return artisanRepository.save(artisan);
    }

    public void deleteArtisan(Long id) {
        artisanRepository.deleteById(id);
    }

    public Artisan updateArtisan(Artisan artisan) {
        return artisanRepository.save(artisan);
    }
}
