package com.artisanat_backend.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private Long id;
    private List<CartItemResponseDto> cartItems;
}
