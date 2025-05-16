package com.java360.pmanager.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppConfigProperties {

    private final General general;
    private final Security security;

    @Data
    public static class General {
        private final int pageSize;
    }

    @Data
    public static class Security {
        private final String masterApiKey;
        private final Integer expirationDays;
    }

}
