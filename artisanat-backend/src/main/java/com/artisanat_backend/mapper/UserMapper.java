package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.AdminDTO;
import com.artisanat_backend.dto.ArtisanDTO;
import com.artisanat_backend.dto.CustomerDTO;
import com.artisanat_backend.dto.LoginResponse;
import com.artisanat_backend.model.Admin;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // Admin Mappings

    AdminDTO toAdminDTO(Admin admin);

    Admin toAdminEntity(AdminDTO dto);

    // Artisan Mappings
    ArtisanDTO toArtisanDTO(Artisan artisan);

    Artisan toArtisanEntity(ArtisanDTO dto);

    // Customer Mappings
    CustomerDTO toCustomerDTO(Customer customer);

    Customer toCustomerEntity(CustomerDTO dto);

    LoginResponse toUserLoginResponse(User user);
}
