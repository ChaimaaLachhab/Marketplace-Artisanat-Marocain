package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.OrderRequestDto;
import com.artisanat_backend.dto.response.OrderResponseDto;
import com.artisanat_backend.model.Order;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SubOrderMapper.class})
public interface OrderMapper {
    Order toEntity(OrderRequestDto orderRequestDto);
    OrderResponseDto toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderRequestDto orderRequestDto, @MappingTarget Order order);
}
