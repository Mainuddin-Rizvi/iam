package com.example.iam.common.util;

/** Utility for sending SMS */
public class SmsService {
    public void sendSms(String phoneNumber, String message) {
        // In real use, integrate with SMS gateway
        System.out.printf("Sending SMS to %s: %s\n", phoneNumber, message);
    }
}
