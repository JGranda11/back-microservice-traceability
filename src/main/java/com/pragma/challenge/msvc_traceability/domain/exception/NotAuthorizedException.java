package com.pragma.challenge.msvc_traceability.domain.exception;

import com.pragma.challenge.msvc_traceability.domain.util.DomainConstants;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super(DomainConstants.NOT_AUTHORIZED_ERROR_MESSAGE);
    }
}
