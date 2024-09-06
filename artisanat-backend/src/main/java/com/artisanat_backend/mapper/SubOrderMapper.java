package com.artisanat_backend.mapper;

import com.artisanat_backend.model.SubOrder;
import com.artisanat_backend.dto.SubOrderDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderMapper.class, ProductMapper.class})
public interface SubOrderMapper {
    SubOrder toEntity(SubOrderDto subOrderDto);

    SubOrderDto toDto(SubOrder subOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    SubOrder partialUpdate(SubOrderDto subOrderDto, @MappingTarget SubOrder subOrder);
}