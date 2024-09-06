package com.artisanat_backend.mapper;

import com.artisanat_backend.model.Media;
import com.artisanat_backend.dto.MediaDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface MediaMapper {
    Media toEntity(MediaDto mediaDto);

    MediaDto toDto(Media media);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Media partialUpdate(MediaDto mediaDto, @MappingTarget Media media);
}