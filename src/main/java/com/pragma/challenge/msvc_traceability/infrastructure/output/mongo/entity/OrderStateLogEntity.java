package com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.entity;

import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStateLogEntity {
    private OrderState state;
    private LocalDateTime timestamp;
}
