package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.AdminDTO;
import com.artisanat_backend.dto.ArtisanDTO;
import com.artisanat_backend.dto.CustomerDTO;
import com.artisanat_backend.entity.Admin;
import com.artisanat_backend.entity.Artisan;
import com.artisanat_backend.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Admin Mappings
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true)
    })
    AdminDTO toAdminDTO(Admin admin);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true)
    })
    Admin toAdminEntity(AdminDTO dto);

    // Artisan Mappings
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "specialty", source = "specialty"),
        @Mapping(target = "location", source = "location"),
        @Mapping(target = "experience", source = "experience"),
        @Mapping(target = "products", ignore = true)
    })
    ArtisanDTO toArtisanDTO(Artisan artisan);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "specialty", source = "specialty"),
        @Mapping(target = "location", source = "location"),
        @Mapping(target = "experience", source = "experience"),
        @Mapping(target = "products", ignore = true)
    })
    Artisan toArtisanEntity(ArtisanDTO dto);

    // Customer Mappings
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "reviews", ignore = true),
        @Mapping(target = "orders", ignore = true),
        @Mapping(target = "loyalty", ignore = true),
        @Mapping(target = "cart", ignore = true)
    })
    CustomerDTO toCustomerDTO(Customer customer);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "fullName", source = "fullName"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phone", source = "phone"),
        @Mapping(target = "role", ignore = true),
        @Mapping(target = "reviews", ignore = true),
        @Mapping(target = "orders", ignore = true),
        @Mapping(target = "loyalty", ignore = true),
        @Mapping(target = "cart", ignore = true)
    })
    Customer toCustomerEntity(CustomerDTO dto);
}
