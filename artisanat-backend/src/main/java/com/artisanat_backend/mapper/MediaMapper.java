package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.MediaRequestDto;
import com.artisanat_backend.dto.response.MediaResponseDto;
import com.artisanat_backend.model.Media;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface MediaMapper {
    Media toEntity(MediaRequestDto mediaRequestDto);
    MediaResponseDto toDto(Media media);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Media partialUpdate(MediaRequestDto mediaRequestDto, @MappingTarget Media media);
}
