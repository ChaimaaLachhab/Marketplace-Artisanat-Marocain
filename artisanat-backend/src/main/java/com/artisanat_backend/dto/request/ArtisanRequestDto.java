package com.artisanat_backend.dto.request;

import com.artisanat_backend.enums.Specialty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtisanRequestDto {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Specialty specialty;
    private String location;
    private int experience;
}
