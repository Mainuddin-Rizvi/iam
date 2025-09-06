package com.example.iam.core.service;

import com.example.iam.common.dto.MfaEnrollResponse;
import java.util.UUID;

/** MFA service */
public interface MfaService {
    MfaEnrollResponse enrollTotp(UUID userId);
    boolean verifyTotp(UUID userId, String code);
    void sendSmsOtp(UUID userId);
    boolean verifySmsOtp(UUID userId, String code);
}
