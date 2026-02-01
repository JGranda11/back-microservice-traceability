package com.pragma.challenge.msvc_traceability.domain.spi;

import com.pragma.challenge.msvc_traceability.domain.model.security.AuthorizedUser;

public interface AuthorizationSecurityPort {
    AuthorizedUser authorize(String token);
}
