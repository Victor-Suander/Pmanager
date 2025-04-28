package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class InvalidTaskStatusException extends RequestException {

    public InvalidTaskStatusException(String statusStr) {
        super("InvalidTaskStatus", "Invalid task status: " + statusStr);
    }

}
