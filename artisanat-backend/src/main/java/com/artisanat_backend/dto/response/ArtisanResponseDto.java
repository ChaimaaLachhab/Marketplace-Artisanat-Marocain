package com.artisanat_backend.dto.response;

import com.artisanat_backend.enums.Specialty;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtisanResponseDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private Specialty specialty;
    private String location;
    private int experience;
    private VerificationStatus verificationStatus;
    private List<Product> products;
    private Media userPhoto;
}
