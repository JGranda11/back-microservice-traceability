package com.pragma.challenge.msvc_traceability.domain.util;

public class TokenHolder {
    private TokenHolder() {
        throw new UnsupportedOperationException("TokenContainer is a utility class and cannot be instantiated");
    }


    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenHolder.token = token;
    }
}
