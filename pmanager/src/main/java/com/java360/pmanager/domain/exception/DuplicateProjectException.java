package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class DuplicateProjectException extends RequestException {

    public DuplicateProjectException(String name) {
        super("ProjectDuplicate", "A project with the name already exists: " + name);
    }

}
