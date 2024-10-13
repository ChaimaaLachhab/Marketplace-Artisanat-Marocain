package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private List<Order> orders;
    private Loyalty loyalty;
    private Cart cart;
    private Media userPhoto;
}
