package com.java360.pmanager.domain.exception;

import com.java360.pmanager.infrastructure.exception.RequestException;

public class MemberNotFoundException extends RequestException {

    public MemberNotFoundException(String memberId) {
        super("MemberNotFound", "Member not found: " + memberId);
    }

}
