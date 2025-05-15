package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class ApiKeyNotFoundException extends RequestException {

    public ApiKeyNotFoundException(String apiKeyId) {
        super("ApiKeyNotFound", "The API key was not found: " + apiKeyId);
    }

}
