package com.artisanat_backend.controller;

import com.artisanat_backend.dto.request.ArtisanRequestDto;
import com.artisanat_backend.dto.response.ArtisanResponseDto;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.service.ArtisanService;
import com.artisanat_backend.enums.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing artisans.
 */
@RestController
@RequestMapping("/api/artisans")
public class ArtisanController {

    private final ArtisanService artisanService;
    private final UserMapper userMapper;

    @Autowired
    public ArtisanController(ArtisanService artisanService, UserMapper userMapper) {
        this.artisanService = artisanService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieve all artisans.
     *
     * @return List of all artisans.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<ArtisanResponseDto>> getAllArtisans() {
        List<Artisan> artisans = artisanService.getAllArtisans();
        List<ArtisanResponseDto> artisanResponseDtos = artisans.stream()
                .map(userMapper::toArtisanResponseDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(artisanResponseDtos, HttpStatus.OK);
    }

    /**
     * Retrieve an artisan by ID.
     *
     * @return The artisan with the specified ID.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @GetMapping("/details")
    public ResponseEntity<ArtisanResponseDto> getArtisanById(@AuthenticationPrincipal Artisan currentArtisan) {
        Artisan artisan = artisanService.getArtisanById(currentArtisan.getId());
        return artisan != null
                ? new ResponseEntity<>(userMapper.toArtisanResponseDto(artisan), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Update an existing artisan.
     *
     * @param artisanDTO The artisan with updated information.
     * @param userPhoto  The photo to be updated, if any.
     * @param artisan    The authenticated artisan.
     * @return The updated artisan.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArtisanResponseDto> updateArtisan(
            @RequestPart("artisan") ArtisanRequestDto artisanDTO,
            @RequestPart(value = "userPhoto", required = false) MultipartFile userPhoto,
            @AuthenticationPrincipal Artisan artisan) {

        Artisan updatedArtisan = artisanService.updateArtisan(artisanDTO, artisan, userPhoto);
        return new ResponseEntity<>(userMapper.toArtisanResponseDto(updatedArtisan), HttpStatus.OK);
    }

    /**
     * Retrieve all pending artisans.
     *
     * @return List of all pending artisans.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pending")
    public ResponseEntity<List<ArtisanResponseDto>> getPendingArtisans() {
        List<Artisan> pendingArtisans = artisanService.getPendingArtisans();
        List<ArtisanResponseDto> artisanResponseDtos = pendingArtisans.stream()
                .map(userMapper::toArtisanResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(artisanResponseDtos);
    }

    /**
     * Verify an artisan.
     *
     * @param id     The ID of the artisan to verify.
     * @param status The verification status.
     * @return The verified artisan.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/verify/{id}")
    public ResponseEntity<ArtisanResponseDto> verifyArtisan(@PathVariable Long id, @RequestParam VerificationStatus status) {
        Artisan artisan = artisanService.verifyArtisan(id, status);
        return ResponseEntity.ok(userMapper.toArtisanResponseDto(artisan));
    }
}
