package com.artisanat_backend.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDto {
    private List<CartItemRequestDto> cartItems;
}
