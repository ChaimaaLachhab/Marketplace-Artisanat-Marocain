package com.artisanat_backend.dto.request;

import com.artisanat_backend.model.SubOrderItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderRequestDto {
    private double subTotal;
    private Long artisanId;
    private List<SubOrderItem> subOrderItems;
}
