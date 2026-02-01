package com.pragma.challenge.msvc_traceability.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewOrderLogRequest {
    @NotNull(message = "'order id' field must not be null")
    private Long orderId;
    @NotNull(message = "'customer id' field must not be null")
    private String customerId;
    @NotNull(message = "'restaurant id' field must not be null")
    private String restaurantId;
}
