package com.artisanat_backend.dto.request;

import com.artisanat_backend.enums.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private int quantity;
    private String orderDate;
    private String location;
    private Status status;
    private List<Long> productIds;
}
