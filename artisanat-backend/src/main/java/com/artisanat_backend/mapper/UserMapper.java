package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.AdminDTO;
import com.artisanat_backend.dto.ArtisanDTO;
import com.artisanat_backend.dto.CustomerDTO;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Admin Mappings

    AdminDTO toAdminDTO(Admin admin);

    Admin toAdminEntity(AdminDTO dto);

    // Artisan Mappings
    ArtisanDTO toArtisanDTO(Artisan artisan);

    Artisan toArtisanEntity(ArtisanDTO dto);
    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

    // Customer Mappings
    CustomerDTO toCustomerDTO(Customer customer);

    Customer toCustomerEntity(CustomerDTO dto);
}
