package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Type;
import com.artisanat_backend.model.Media;

/**
 * DTO for {@link Media}
 */
public class MediaDto {
    String mediaUrl;
    Type type;
    ProductDto product;
}