package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.SubOrderRequestDto;
import com.artisanat_backend.dto.response.SubOrderResponseDto;
import com.artisanat_backend.model.SubOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderMapper.class, SubOrderItemMapper.class, ProductMapper.class})
public interface SubOrderMapper {
    SubOrder toEntity(SubOrderRequestDto subOrderRequestDto);
    SubOrderResponseDto toDto(SubOrder subOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubOrder partialUpdate(SubOrderRequestDto subOrderRequestDto, @MappingTarget SubOrder subOrder);
}
