package com.artisanat_backend.service;

import com.artisanat_backend.dto.CloudinaryResponse;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {

    @Autowired
    private CloudinaryService cloudinaryService;

    public Media handleMediaUpload(MultipartFile file, Product product) {
        FileUploadUtil.assertAllowed(file, FileUploadUtil.MEDIA_PATTERN);
        String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName, "image");

        Media media = new Media();
        media.setMediaUrl(response.getUrl());
        media.setMediaId(response.getPublicId());
        media.setProduct(product);

        return media;
    }
}
