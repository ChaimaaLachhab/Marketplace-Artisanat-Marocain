package com.artisanat_backend.dto.request;

import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private String description;
    private float price;
    private int stock;
    private Category category;
    private Collection collection;
}
