package com.artisanat_backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
    private int rating;
    private String comment;
}
