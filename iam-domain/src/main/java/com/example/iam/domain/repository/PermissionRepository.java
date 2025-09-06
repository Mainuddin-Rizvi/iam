package com.example.iam.domain.repository;

import com.example.iam.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

/** Repository for Permission */
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Permission findByName(String name);
}
