package com.example.iam.core.mapper;

import com.example.iam.common.dto.UserDto;
import com.example.iam.domain.entity.User;
import org.mapstruct.Mapper;

/** Mapper for User entity <-> DTO */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);
    User toEntity(UserDto dto);
}
