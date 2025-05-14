package com.java360.pmanager.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

    public Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if (!Objects.equals(apiKey, "thekey")) {
            throw new BadCredentialsException("Invalid API key: "  +  apiKey);
        }

        return new ApiKeyAuthenticationToken(apiKey);
    }
}
