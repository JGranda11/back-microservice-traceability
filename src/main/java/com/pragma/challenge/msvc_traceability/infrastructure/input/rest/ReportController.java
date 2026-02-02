package com.pragma.challenge.msvc_traceability.infrastructure.input.rest;

import com.pragma.challenge.msvc_traceability.application.dto.response.EmployeeReportResponse;
import com.pragma.challenge.msvc_traceability.application.dto.response.OrderReportResponse;
import com.pragma.challenge.msvc_traceability.application.handler.ReportHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportHandler reportHandler;

    @Operation(
            summary = "Create order report"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order report created successfully",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = OrderReportResponse.class)
                            )
                    )
            )
    })
    @GetMapping("/orders")
    public ResponseEntity<List<OrderReportResponse>> getOrdersReport(){
        return ResponseEntity.ok(
                reportHandler.getOrdersReport()
        );
    }


    @Operation(
            summary = "Create employees report"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employees report created successfully",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = OrderReportResponse.class)
                            )
                    )
            )
    })
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeReportResponse>> getEmployeesReport(){
        return ResponseEntity.ok(
                reportHandler.getEmployeesReport()
        );
    }
}
