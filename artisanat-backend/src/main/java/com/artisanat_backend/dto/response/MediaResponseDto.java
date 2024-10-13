package com.artisanat_backend.dto.response;

import com.artisanat_backend.enums.Type;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponseDto {
    private Long id;
    private String mediaUrl;
    private String mediaId;
    private Type type;
    private Product product;
    private User user;
}
