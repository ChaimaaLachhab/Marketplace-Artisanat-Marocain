package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.AdminRequestDto;
import com.artisanat_backend.dto.response.AdminResponseDto;
import com.artisanat_backend.dto.request.ArtisanRequestDto;
import com.artisanat_backend.dto.request.CustomerRequestDto;
import com.artisanat_backend.dto.response.ArtisanResponseDto;
import com.artisanat_backend.dto.response.CustomerResponseDto;
import com.artisanat_backend.model.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface UserMapper {

    // Admin Mappings
    AdminResponseDto toAdminResponseDto(Admin admin);
    Admin toAdminEntity(AdminRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Admin partialUpdateAdmin(AdminRequestDto adminRequestDto, @MappingTarget Admin admin);

    // Artisan Mappings
    ArtisanResponseDto toArtisanResponseDto(Artisan artisan);
    Artisan toArtisanEntity(ArtisanRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Artisan partialUpdateArtisan(ArtisanRequestDto artisanRequestDto, @MappingTarget Artisan artisan);

    // Customer Mappings
    CustomerResponseDto toCustomerResponseDto(Customer customer);
    Customer toCustomerEntity(CustomerRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdateCustomer(CustomerRequestDto customerRequestDto, @MappingTarget Customer customer);
}
