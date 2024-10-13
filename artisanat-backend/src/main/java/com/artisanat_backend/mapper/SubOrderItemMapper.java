package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.SubOrderItemRequestDto;
import com.artisanat_backend.dto.request.SubOrderRequestDto;
import com.artisanat_backend.dto.response.SubOrderItemResponseDto;
import com.artisanat_backend.dto.response.SubOrderResponseDto;
import com.artisanat_backend.model.SubOrder;
import com.artisanat_backend.model.SubOrderItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderMapper.class, SubOrderItemMapper.class, ProductMapper.class})
public interface SubOrderItemMapper {
    SubOrderItem toEntity(SubOrderItemRequestDto subOrderItemRequestDto);
    SubOrderItemResponseDto toDto(SubOrderItem subOrderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubOrderItem partialUpdate(SubOrderItemRequestDto subOrderItemRequestDto, @MappingTarget SubOrderItem subOrderItem);
}
