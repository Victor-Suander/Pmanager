package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class DuplicateMemberException extends RequestException {

    public DuplicateMemberException(String email) {
        super("MemberDuplicate", "A member with the e-mail already exists: " + email);
    }

}
