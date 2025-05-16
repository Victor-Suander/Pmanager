package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class ApiKeyExpiredException extends RequestException {

    public ApiKeyExpiredException(String apiKeyId) {
        super("ApiKeyExpired", "The API key expired: " + apiKeyId);
    }

}
