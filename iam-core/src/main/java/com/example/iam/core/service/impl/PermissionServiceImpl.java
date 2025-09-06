package com.example.iam.core.service.impl;

import com.example.iam.common.dto.PermissionDto;
import com.example.iam.core.service.PermissionService;
import com.example.iam.domain.entity.Permission;
import com.example.iam.domain.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/** Permission service implementation */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repo;

    public PermissionServiceImpl(PermissionRepository repo) {
        this.repo = repo;
    }

    @Override
    public PermissionDto createPermission(PermissionDto dto) {
        Permission p = new Permission();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        repo.save(p);
        dto.setId(p.getId());
        return dto;
    }

    @Override
    public PermissionDto updatePermission(UUID id, PermissionDto dto) {
        Permission p = repo.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        return dto;
    }

    @Override
    public void deletePermission(UUID id) {
        repo.deleteById(id);
    }

    @Override
    public PermissionDto findById(UUID id) {
        Permission p = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        PermissionDto dto = new PermissionDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        return dto;
    }

    @Override
    public List<PermissionDto> listPermissions() {
        return repo.findAll().stream().map(p -> {
            PermissionDto dto = new PermissionDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }
}
