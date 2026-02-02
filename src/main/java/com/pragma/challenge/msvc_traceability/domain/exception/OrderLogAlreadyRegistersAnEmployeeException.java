package com.pragma.challenge.msvc_traceability.domain.exception;


import com.pragma.challenge.msvc_traceability.domain.util.DomainConstants;

public class OrderLogAlreadyRegistersAnEmployeeException extends RuntimeException {

    public OrderLogAlreadyRegistersAnEmployeeException(){
        super(DomainConstants.ORDER_LOG_ALREADY_HAS_AN_ASSIGNED_EMPLOYEE);
    }
}