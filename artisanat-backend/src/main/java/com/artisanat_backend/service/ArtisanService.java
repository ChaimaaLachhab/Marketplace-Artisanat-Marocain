package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.ArtisanRequestDto;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.repository.ArtisanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ArtisanService {

    private final ArtisanRepository artisanRepository;
    private final MediaService mediaService;
    private final UserMapper userMapper;

    @Autowired
    public ArtisanService(ArtisanRepository artisanRepository, MediaService mediaService, UserMapper userMapper) {
        this.artisanRepository = artisanRepository;
        this.mediaService = mediaService;
        this.userMapper = userMapper;
    }

    public List<Artisan> getAllArtisans() {
        return artisanRepository.findAll();
    }

    public Artisan getArtisanById(Long id) {
        return artisanRepository.findById(id).orElse(null);
    }

    public void deleteArtisan(Long id) {
        artisanRepository.deleteById(id);
    }

    public Artisan updateArtisan(ArtisanRequestDto artisanDTO, Artisan artisan, MultipartFile userPhoto) {
        Artisan newArtisan = userMapper.partialUpdateArtisan(artisanDTO, artisan);

        if (userPhoto != null && !userPhoto.isEmpty()) {
            Media media=  mediaService.updateMediaForUser(userPhoto, newArtisan);
            newArtisan.setUserPhoto(media);
            return artisanRepository.save(newArtisan);
        }

        return artisanRepository.save(newArtisan);
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
