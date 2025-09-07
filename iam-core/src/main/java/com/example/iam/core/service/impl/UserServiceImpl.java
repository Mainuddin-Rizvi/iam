package com.example.iam.core.service.impl;

import com.example.iam.common.dto.UserDto;
import com.example.iam.common.exception.TenantNotFoundException;
import com.example.iam.common.exception.UserNotFoundException;
import com.example.iam.core.mapper.UserMapper;
import com.example.iam.core.service.UserService;
import com.example.iam.domain.entity.Tenant;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.repository.TenantRepository;
import com.example.iam.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/** User service implementation */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final TenantRepository tenantRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, TenantRepository tenantRepo,
                           UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tenantRepo = tenantRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UUID tenantId, UserDto dto) {
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found"));

        User user = userMapper.toEntity(dto);
        user.setTenant(tenant);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        user.setEnabled(true);

        // Set default password (username + "123")
        String defaultPassword = dto.getUsername() + "123";
        user.setPasswordHash(passwordEncoder.encode(defaultPassword));

        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(UUID tenantId, UUID userId, UserDto dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setUpdatedAt(Instant.now());

        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void disableUser(UUID tenantId, UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEnabled(false);
        userRepo.save(user);
    }

    @Override
    public UserDto findByUsername(UUID tenantId, String username) {
        // For demo purposes, search across all users
        User user = userRepo.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        return userMapper.toDto(user);
    }

    @Override
    public UserDto findById(UUID tenantId, UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> listUsers(UUID tenantId) {
        Tenant tenant = tenantRepo.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant not found"));

        return userRepo.findByTenant(tenant).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}