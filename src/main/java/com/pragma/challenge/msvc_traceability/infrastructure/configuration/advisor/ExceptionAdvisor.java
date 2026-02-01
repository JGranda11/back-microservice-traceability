package com.pragma.challenge.msvc_traceability.infrastructure.configuration.advisor;

import com.pragma.challenge.msvc_traceability.domain.exception.*;
import com.pragma.challenge.msvc_traceability.infrastructure.configuration.advisor.response.ExceptionResponse;
import com.pragma.challenge.msvc_traceability.infrastructure.configuration.advisor.response.ValidationExceptionResponse;
import com.pragma.challenge.msvc_traceability.infrastructure.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleNotAuthorized(NotAuthorizedException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(OrderLogAlreadyHasThatStateException.class)
    public ResponseEntity<ExceptionResponse> handleOrderLogAlreadyHasThatState(OrderLogAlreadyHasThatStateException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderLogAlreadyRegistersAnEmployeeException.class)
    public ResponseEntity<ExceptionResponse> handleOrderLogAlreadyRegistersAnEmployee(OrderLogAlreadyRegistersAnEmployeeException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(field -> {
                    StringBuilder sb = new StringBuilder();
                    String rejectedValue = field.getRejectedValue() == null ? "null" : field.getRejectedValue().toString();
                    sb.append(field.getDefaultMessage()).append(": ").append(rejectedValue);
                    return sb.toString();
                }).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
