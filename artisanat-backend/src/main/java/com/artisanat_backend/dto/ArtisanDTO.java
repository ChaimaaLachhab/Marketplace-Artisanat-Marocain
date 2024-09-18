package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Specialty;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.SubOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * DTO for {@link Artisan}
 */
@Getter
@Setter
public class ArtisanDTO extends UserDTO {
    private Specialty specialty;
    private String location;
    private int experience;
}
