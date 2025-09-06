package com.example.iam.common.util;

import java.time.Instant;

/** Provides current time for auditing/timestamps */
public class ClockProvider {
    public Instant now() {
        return Instant.now();
    }
}
