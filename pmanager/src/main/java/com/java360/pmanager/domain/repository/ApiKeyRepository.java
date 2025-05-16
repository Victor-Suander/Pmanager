package com.java360.pmanager.domain.repository;

import com.java360.pmanager.domain.document.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {

    Optional<ApiKey> findByValue(String value);
}
