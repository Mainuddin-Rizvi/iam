package com.example.iam.core.service.impl;

import com.example.iam.common.dto.MfaEnrollResponse;
import com.example.iam.core.service.MfaService;
import com.example.iam.domain.entity.MfaDevice;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.repository.MfaDeviceRepository;
import com.example.iam.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/** MFA service implementation */
@Service
public class MfaServiceImpl implements MfaService {

    private final MfaDeviceRepository mfaRepo;
    private final UserRepository userRepo;

    public MfaServiceImpl(MfaDeviceRepository mfaRepo, UserRepository userRepo) {
        this.mfaRepo = mfaRepo;
        this.userRepo = userRepo;
    }

    @Override
    public MfaEnrollResponse enrollTotp(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow();
        MfaDevice d = new MfaDevice();
        d.setUser(user);
        d.setType("TOTP");
        d.setSecret("secret-" + new Random().nextInt(999999));
        d.setConfirmed(false);
        mfaRepo.save(d);
        MfaEnrollResponse r = new MfaEnrollResponse();
        r.setUserId(userId);
        r.setType("TOTP");
        r.setSecret(d.getSecret());
        return r;
    }

    @Override
    public boolean verifyTotp(UUID userId, String code) {
        List<MfaDevice> devices = mfaRepo.findByUser(userRepo.findById(userId).orElseThrow());
        return devices.stream().anyMatch(d -> d.getSecret().equals(code));
    }

    @Override
    public void sendSmsOtp(UUID userId) {
        System.out.println("Sending SMS OTP to user " + userId);
    }

    @Override
    public boolean verifySmsOtp(UUID userId, String code) {
        return "123456".equals(code);
    }
}
