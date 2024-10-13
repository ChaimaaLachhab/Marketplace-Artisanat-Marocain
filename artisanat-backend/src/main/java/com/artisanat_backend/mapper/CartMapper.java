package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.CartRequestDto;
import com.artisanat_backend.dto.response.CartResponseDto;
import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class, CartItemMapper.class})
public interface CartMapper {
    Cart toEntity(CartRequestDto cartRequestDto);

    @AfterMapping
    default void linkCustomer(@MappingTarget Cart cart) {
        Customer customer = cart.getCustomer();
        if (customer != null) {
            customer.setCart(cart);
        }
    }

    CartResponseDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartRequestDto cartRequestDto, @MappingTarget Cart cart);
}
