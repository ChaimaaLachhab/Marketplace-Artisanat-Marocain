package com.artisanat_backend.dto.request;

import com.artisanat_backend.enums.Type;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaRequestDto {
    private String mediaUrl;
    private String mediaId;
    private Type type;
    private Long productId;
    private Long userId;
}
