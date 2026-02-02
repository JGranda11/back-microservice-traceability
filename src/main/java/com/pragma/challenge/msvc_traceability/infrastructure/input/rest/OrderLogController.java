package com.pragma.challenge.msvc_traceability.infrastructure.input.rest;

import com.pragma.challenge.msvc_traceability.application.dto.request.NewOrderLogRequest;
import com.pragma.challenge.msvc_traceability.application.dto.response.ShortOrderLogResponse;
import com.pragma.challenge.msvc_traceability.application.handler.OrderLogHandler;
import com.pragma.challenge.msvc_traceability.domain.util.enums.OrderState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order-logs")
@RequiredArgsConstructor
public class OrderLogController {
    private final OrderLogHandler orderLogHandler;

    @Operation(
            summary = "Create a new order log",
            description = "Stores a new order log with the initial state.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Order log created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShortOrderLogResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ShortOrderLogResponse> saveNewOrderLog(@Valid @RequestBody NewOrderLogRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderLogHandler.saveNewOrderLog(request));
    }

    @Operation(
            summary = "Add a new state to an order log",
            description = "Updates the state of an existing order log.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order state updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShortOrderLogResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid state provided"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Order log not found"
                    )
            }
    )
    @PatchMapping("/orders/{orderId}/state")
    public ResponseEntity<ShortOrderLogResponse> addNewState(
            @PathVariable Long orderId,
            @RequestParam OrderState add) {
        return ResponseEntity.ok(orderLogHandler.addNewStateToOrder(orderId, add));
    }

    @Operation(
            summary = "Assign an employee to an order log",
            description = "Assigns an employee to an order log and updates the status to PREPARING.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Employee assigned successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShortOrderLogResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Order log not found"
                    )
            }
    )
    @PatchMapping("/orders/{orderId}/assigned-employee")
    public ResponseEntity<ShortOrderLogResponse> addAssignedEmployee(
            @PathVariable Long orderId,
            @RequestParam String add) {
        return ResponseEntity.ok(orderLogHandler.addEmployeeToOrder(orderId, add));
    }
}
