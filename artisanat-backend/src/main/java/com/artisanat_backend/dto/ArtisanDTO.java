package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Specialty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtisanDTO extends UserDTO {
    private Specialty specialty;
    private String location;
    private int experience;
}
