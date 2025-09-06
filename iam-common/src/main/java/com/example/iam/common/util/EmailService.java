package com.example.iam.common.util;

import java.util.Map;

/** Utility for sending emails */
public class EmailService {
    public void sendEmail(String to, String subject, String template, Map<String,Object> ctx) {
        // In real use, integrate with SMTP provider
        System.out.printf("Sending email to %s: %s\n", to, subject);
    }
}
