package com.artisanat_backend.dto;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.SubOrder;

import java.util.List;

/**
 * DTO for {@link SubOrder}
 */
public class SubOrderDto {
    double subTotal;
    Artisan artisan;
    OrderDto order;
    List<ProductDto> products;
}