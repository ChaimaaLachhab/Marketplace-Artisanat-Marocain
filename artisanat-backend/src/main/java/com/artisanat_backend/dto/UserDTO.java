package com.artisanat_backend.dto;

import com.artisanat_backend.model.User;
import lombok.Getter;
import lombok.Setter;
/**
 * DTO for {@link User}
 */
@Getter
@Setter
public abstract class UserDTO {
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
}
