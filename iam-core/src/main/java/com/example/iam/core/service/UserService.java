package com.example.iam.core.service;

import com.example.iam.common.dto.UserDto;
import java.util.List;
import java.util.UUID;

/** User service */
public interface UserService {
    UserDto createUser(UUID tenantId, UserDto dto);
    UserDto updateUser(UUID tenantId, UUID userId, UserDto dto);
    void disableUser(UUID tenantId, UUID userId);
    UserDto findById(UUID tenantId, UUID userId);
    UserDto findByUsername(UUID tenantId, String username);
    List<UserDto> listUsers(UUID tenantId);
    boolean validatePassword(String rawPassword, String encodedPassword);
}