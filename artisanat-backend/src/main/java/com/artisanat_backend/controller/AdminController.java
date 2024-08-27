package com.artisanat_backend.controller;

import com.artisanat_backend.entity.Admin;
import com.artisanat_backend.entity.Admin;
import com.artisanat_backend.service.AdminService;
import com.artisanat_backend.service.ArtisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> getAllArtisans() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getArtisanById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Admin createArtisan(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateArtisan(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setId(id);
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteArtisan(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }
}
