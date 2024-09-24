package com.artisanat_backend.service;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.repository.ArtisanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArtisanServiceTest {

    @Mock
    private ArtisanRepository artisanRepository;

    @InjectMocks
    private ArtisanService artisanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllArtisans() {
        List<Artisan> artisans = List.of(new Artisan(), new Artisan());
        when(artisanRepository.findAll()).thenReturn(artisans);

        List<Artisan> result = artisanService.getAllArtisans();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(artisanRepository, times(1)).findAll();
    }

    @Test
    void getArtisanById() {
        Artisan artisan = new Artisan();
        when(artisanRepository.findById(1L)).thenReturn(Optional.of(artisan));

        Artisan result = artisanService.getArtisanById(1L);

        assertNotNull(result);
        assertEquals(artisan, result);
        verify(artisanRepository, times(1)).findById(1L);
    }

    @Test
    void getArtisanById_NotFound() {
        when(artisanRepository.findById(1L)).thenReturn(Optional.empty());

        Artisan result = artisanService.getArtisanById(1L);

        assertNull(result);
        verify(artisanRepository, times(1)).findById(1L);
    }

    @Test
    void deleteArtisan() {
        artisanService.deleteArtisan(1L);

        verify(artisanRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateArtisan() {
        
        Artisan artisan = new Artisan();
        when(artisanRepository.save(artisan)).thenReturn(artisan);

        Artisan result = artisanService.updateArtisan(artisan);

        assertNotNull(result);
        assertEquals(artisan, result);
        verify(artisanRepository, times(1)).save(artisan);
    }

    @Test
    void getPendingArtisans() {
        
        List<Artisan> pendingArtisans = List.of(new Artisan(), new Artisan());
        when(artisanRepository.findByVerificationStatus(VerificationStatus.PENDING)).thenReturn(pendingArtisans);

        List<Artisan> result = artisanService.getPendingArtisans();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(artisanRepository, times(1)).findByVerificationStatus(VerificationStatus.PENDING);
    }

    @Test
    void verifyArtisan() {
        
        Artisan artisan = new Artisan();
        when(artisanRepository.findById(1L)).thenReturn(Optional.of(artisan));
        when(artisanRepository.save(artisan)).thenReturn(artisan);

        Artisan result = artisanService.verifyArtisan(1L, VerificationStatus.APPROVED);

        assertNotNull(result);
        assertEquals(VerificationStatus.APPROVED, result.getVerificationStatus());
        verify(artisanRepository, times(1)).findById(1L);
        verify(artisanRepository, times(1)).save(artisan);
    }

    @Test
    void verifyArtisan_NotFound() {
        
        when(artisanRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            artisanService.verifyArtisan(1L, VerificationStatus.APPROVED);
        });
        verify(artisanRepository, times(1)).findById(1L);
    }
}
