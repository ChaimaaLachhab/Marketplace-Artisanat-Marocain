package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.dto.response.ReviewResponseDto;
import com.artisanat_backend.model.Review;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    Review toEntity(ReviewRequestDto reviewRequestDto);
    ReviewResponseDto toDto(Review review);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review partialUpdate(ReviewRequestDto reviewRequestDto, @MappingTarget Review review);
}
