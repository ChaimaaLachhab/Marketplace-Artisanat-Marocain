package com.artisanat_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequestDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
}
