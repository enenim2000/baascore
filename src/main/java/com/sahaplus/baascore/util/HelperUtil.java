package com.sahaplus.baascore.util;

import org.springframework.stereotype.Component;

@Component
public class HelperUtil {
    public String generateTrackingRef() {
        return java.util.UUID.randomUUID().toString();
    }
}

