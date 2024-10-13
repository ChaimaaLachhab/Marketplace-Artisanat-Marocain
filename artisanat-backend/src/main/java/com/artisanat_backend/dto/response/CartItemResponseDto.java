package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {
    private Long id;
    private int quantity;
    private ProductResponseDto product;
}
