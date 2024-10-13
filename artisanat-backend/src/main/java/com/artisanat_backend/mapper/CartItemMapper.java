package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.CartItemRequestDto;
import com.artisanat_backend.dto.response.CartItemResponseDto;
import com.artisanat_backend.model.CartItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface CartItemMapper {
    CartItem toEntity(CartItemRequestDto cartItemRequestDto);
    CartItemResponseDto toDto(CartItem cartItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CartItem partialUpdate(CartItemRequestDto cartItemRequestDto, @MappingTarget CartItem cartItem);
}