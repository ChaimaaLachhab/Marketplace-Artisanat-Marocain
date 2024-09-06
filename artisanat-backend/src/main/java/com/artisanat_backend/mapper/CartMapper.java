package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.CartDto;
import com.artisanat_backend.model.Cart;
import com.artisanat_backend.model.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface CartMapper {
    Cart toEntity(CartDto cartDto);

    @AfterMapping
    default void linkCustomer(@MappingTarget Cart cart) {
        Customer customer = cart.getCustomer();
        if (customer != null) {
            customer.setCart(cart);
        }
    }

    CartDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cart partialUpdate(CartDto cartDto, @MappingTarget Cart cart);
}