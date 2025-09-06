package com.example.iam.core.mapper;

import com.example.iam.common.dto.TenantDto;
import com.example.iam.domain.entity.Tenant;
import org.mapstruct.Mapper;

/** Mapper for Tenant entity <-> DTO */
@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantDto toDto(Tenant entity);
    Tenant toEntity(TenantDto dto);
}
