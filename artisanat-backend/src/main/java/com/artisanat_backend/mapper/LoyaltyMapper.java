package com.artisanat_backend.mapper;

import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import com.artisanat_backend.dto.LoyaltyDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoyaltyMapper {
    Loyalty toEntity(LoyaltyDto loyaltyDto);

    @AfterMapping
    default void linkCustomer(@MappingTarget Loyalty loyalty) {
        Customer customer = loyalty.getCustomer();
        if (customer != null) {
            customer.setLoyalty(loyalty);
        }
    }

    LoyaltyDto toDto(Loyalty loyalty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Loyalty partialUpdate(LoyaltyDto loyaltyDto, @MappingTarget Loyalty loyalty);
}