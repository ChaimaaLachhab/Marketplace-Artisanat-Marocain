package com.artisanat_backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemRequestDto {
    private Long productId;
    private int quantity;
}
