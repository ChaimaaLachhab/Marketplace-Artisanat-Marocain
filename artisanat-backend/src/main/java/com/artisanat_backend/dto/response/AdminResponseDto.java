package com.artisanat_backend.dto.response;

import com.artisanat_backend.model.Media;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private Media userPhoto;
}
