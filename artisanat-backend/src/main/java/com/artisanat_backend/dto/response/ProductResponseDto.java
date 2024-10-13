package com.artisanat_backend.dto.response;

import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    String name;
    String description;
    int rating;
    float price;
    int stock;
    LocalDateTime createdAt = LocalDateTime.now();
    Collection collection;
    Category category;
    Artisan artisan;
    private List<Media> media;
    private List<Review> reviews;
}
