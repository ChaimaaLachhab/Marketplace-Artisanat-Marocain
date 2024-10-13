package com.artisanat_backend.service;

import com.artisanat_backend.enums.Type;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.User;
import com.artisanat_backend.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {
    private final MediaUploadService mediaUploadService;
    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaUploadService mediaUploadService, MediaRepository mediaRepository) {
        this.mediaUploadService = mediaUploadService;
        this.mediaRepository = mediaRepository;
    }

    public Media updateMediaForUser(MultipartFile userPhoto, User user) {
        Media media;

        if (user.getUserPhoto() != null) {
            media = user.getUserPhoto();
            Media newMedia = mediaUploadService.handleMediaUpload(userPhoto, user);
            media.setMediaUrl(newMedia.getMediaUrl());
            media.setMediaId(newMedia.getMediaId());
            media.setType(Type.PHOTO);
            mediaRepository.save(media);
        } else {
            media = mediaUploadService.handleMediaUpload(userPhoto, user);
            media.setType(Type.PHOTO);
            mediaRepository.save(media);
            user.setUserPhoto(media);
        }

        return media;
    }
}
