package com.example.iam.domain.repository;

import com.example.iam.domain.entity.Role;
import com.example.iam.domain.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

/** Repository for Role */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findByTenant(Tenant tenant);
}
