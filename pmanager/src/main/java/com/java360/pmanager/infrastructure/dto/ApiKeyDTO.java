package com.java360.pmanager.infrastructure.dto;

import com.java360.pmanager.domain.document.ApiKey;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
public class ApiKeyDTO {

    private final String id;

    private final String value;

    private final Instant expiresWhen;

    public static ApiKeyDTO create(ApiKey apiKey){
        return new ApiKeyDTO(
                apiKey.getId(),
                apiKey.getValue(),
                apiKey.getExpiresWhen()
        );
    }

}
