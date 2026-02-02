package com.pragma.challenge.msvc_traceability.domain.exception;

import com.pragma.challenge.msvc_traceability.domain.util.DomainConstants;

public class MissingStateException extends RuntimeException {
    public MissingStateException(String state) {
        super(String.format(
                DomainConstants.THIS_ORDER_HAS_NOT_STATE,
                state
        ));
    }
}
