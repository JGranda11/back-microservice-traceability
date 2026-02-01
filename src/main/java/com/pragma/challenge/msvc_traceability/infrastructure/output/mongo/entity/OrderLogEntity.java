package com.pragma.challenge.msvc_traceability.infrastructure.output.mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("order_log")
public class OrderLogEntity {
    @Id
    private String id;
    private Long orderId;
    private String customerId;
    private String restaurantId;
    private String assignedEmployeeId;
    private List<OrderStateLogEntity> states;
}
