package com.artisanat_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderItemRequestDto {
    private int quantity;
    private List<Long> productIds;
}
