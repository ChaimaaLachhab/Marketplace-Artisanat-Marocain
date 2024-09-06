package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import com.artisanat_backend.model.Artisan;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.artisanat_backend.model.Product}
 */
public class ProductDto {
    String name;
    String description;
    float price;
    int stock;
    LocalDateTime createdAt;
    Collection collection;
    Category category;
    Artisan artisan;
}