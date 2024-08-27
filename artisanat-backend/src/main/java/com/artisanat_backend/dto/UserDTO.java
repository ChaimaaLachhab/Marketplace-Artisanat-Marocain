package com.artisanat_backend.dto;

import com.artisanat_backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class UserDTO {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
}
