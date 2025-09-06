package com.example.iam.domain.repository;

import com.example.iam.domain.entity.Tenant;
import com.example.iam.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/** Repository for User */
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameAndTenant(String username, Tenant tenant);
    List<User> findByTenant(Tenant tenant);
}
