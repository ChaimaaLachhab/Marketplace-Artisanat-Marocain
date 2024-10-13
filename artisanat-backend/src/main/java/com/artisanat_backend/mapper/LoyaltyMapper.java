package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.response.LoyaltyResponseDto;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Loyalty;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoyaltyMapper {

    @AfterMapping
    default void linkCustomer(@MappingTarget Loyalty loyalty) {
        Customer customer = loyalty.getCustomer();
        if (customer != null) {
            customer.setLoyalty(loyalty);
        }
    }

    LoyaltyResponseDto toDto(Loyalty loyalty);

}
