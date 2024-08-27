package com.artisanat_backend.service;

import com.artisanat_backend.entity.ImageModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageModelService {

    public List<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        List<ImageModel> imageModels = new ArrayList<>();

        for(MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }

}
