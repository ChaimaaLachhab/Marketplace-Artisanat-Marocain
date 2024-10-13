package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderItemResponseDto {
    private Long id;
    private int quantity;
    private ProductResponseDto product;
}
