package com.artisanat_backend.mapper;

import com.artisanat_backend.dto.request.ProductRequestDto;
import com.artisanat_backend.dto.response.ProductResponseDto;
import com.artisanat_backend.model.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductRequestDto productRequestDto);
    ProductResponseDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductRequestDto productRequestDto, @MappingTarget Product product);
}
