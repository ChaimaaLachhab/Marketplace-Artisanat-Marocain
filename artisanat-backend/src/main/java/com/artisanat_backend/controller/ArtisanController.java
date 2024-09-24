package com.artisanat_backend.controller;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.service.ArtisanService;
import com.artisanat_backend.enums.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing artisans.
 */
@RestController
@RequestMapping("/api/artisans")
public class ArtisanController {

    @Autowired
    private ArtisanService artisanService;

    /**
     * Retrieve all artisans.
     *
     * @return List of all artisans.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Artisan>> getAllArtisans() {
        List<Artisan> artisans = artisanService.getAllArtisans();
        return new ResponseEntity<>(artisans, HttpStatus.OK);
    }

    /**
     * Retrieve an artisan by ID.
     *
     * @param id The ID of the artisan to retrieve.
     * @return The artisan with the specified ID.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Artisan> getArtisanById(@PathVariable Long id) {
        Artisan artisan = artisanService.getArtisanById(id);
        return artisan != null ? new ResponseEntity<>(artisan, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update an existing artisan.
     *
     * @param id The ID of the artisan to update.
     * @param artisan The artisan with updated information.
     * @return The updated artisan.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Artisan> updateArtisan(@PathVariable Long id, @RequestBody Artisan artisan) {
        artisan.setId(id);
        Artisan updatedArtisan = artisanService.updateArtisan(artisan);
        return new ResponseEntity<>(updatedArtisan, HttpStatus.OK);
    }

    /**
     * Delete an artisan by ID.
     *
     * @param id The ID of the artisan to delete.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArtisan(@PathVariable Long id) {
        artisanService.deleteArtisan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieve all pending artisans.
     *
     * @return List of all pending artisans.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pending")
    public ResponseEntity<List<Artisan>> getPendingArtisans() {
        List<Artisan> pendingArtisans = artisanService.getPendingArtisans();
        return ResponseEntity.ok(pendingArtisans);
    }

    /**
     * Verify an artisan.
     *
     * @param id The ID of the artisan to verify.
     * @param status The verification status.
     * @return The verified artisan.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/verify/{id}")
    public ResponseEntity<Artisan> verifyArtisan(@PathVariable Long id, @RequestParam VerificationStatus status) {
        Artisan artisan = artisanService.verifyArtisan(id, status);
        return ResponseEntity.ok(artisan);
    }
}
