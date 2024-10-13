package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderResponseDto {
    private Long id;
    private double subTotal;
    private Artisan artisan;
    private List<SubOrderItemResponseDto> subOrderItems;
}
