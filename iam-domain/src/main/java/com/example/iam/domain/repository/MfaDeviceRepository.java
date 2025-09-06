package com.example.iam.domain.repository;

import com.example.iam.domain.entity.MfaDevice;
import com.example.iam.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

/** Repository for MFA devices */
public interface MfaDeviceRepository extends JpaRepository<MfaDevice, UUID> {
    List<MfaDevice> findByUser(User user);
}
