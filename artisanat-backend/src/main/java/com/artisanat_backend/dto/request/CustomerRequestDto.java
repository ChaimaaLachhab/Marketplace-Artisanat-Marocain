package com.artisanat_backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
}
