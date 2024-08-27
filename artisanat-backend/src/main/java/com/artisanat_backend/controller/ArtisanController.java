package com.artisanat_backend.controller;

import com.artisanat_backend.entity.Artisan;
import com.artisanat_backend.service.ArtisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artisans")
public class ArtisanController {

    @Autowired
    private ArtisanService artisanService;

    @GetMapping
    public List<Artisan> getAllArtisans() {
        return artisanService.getAllArtisans();
    }

    @GetMapping("/{id}")
    public Artisan getArtisanById(@PathVariable Long id) {
        return artisanService.getArtisanById(id);
    }

    @PostMapping
    public Artisan createArtisan(@RequestBody Artisan artisan) {
        return artisanService.createArtisan(artisan);
    }

    @PutMapping("/{id}")
    public Artisan updateArtisan(@PathVariable Long id, @RequestBody Artisan artisan) {
        artisan.setId(id);
        return artisanService.updateArtisan(artisan);
    }

    @DeleteMapping("/{id}")
    public void deleteArtisan(@PathVariable Long id) {
        artisanService.deleteArtisan(id);
    }
}
