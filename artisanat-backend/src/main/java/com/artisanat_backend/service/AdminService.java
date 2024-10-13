package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.AdminRequestDto;
import com.artisanat_backend.enums.Type;
import com.artisanat_backend.mapper.UserMapper;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final MediaService mediaService;
    private final UserMapper userMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, MediaService mediaService, UserMapper userMapper) {
        this.adminRepository = adminRepository;
        this.mediaService = mediaService;
        this.userMapper = userMapper;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin updateAdmin(AdminRequestDto adminDTO, Admin admin, MultipartFile userPhoto) {
        Admin newadmin = userMapper.partialUpdateAdmin(adminDTO, admin);

        if (userPhoto != null && !userPhoto.isEmpty()) {
            Media media = mediaService.updateMediaForUser(userPhoto, newadmin);
            media.setType(Type.PHOTO);
            newadmin.setUserPhoto(media);
        }

        return adminRepository.save(newadmin);
    }
}
