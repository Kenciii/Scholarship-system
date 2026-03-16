package com.example.stipendije.controller;

import com.example.stipendije.dto.PeriodRequestDTO;
import com.example.stipendije.service.ApplicationPeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Application Periods", description = "Admin endpoints for managing scholarship timelines")
@RestController
@RequestMapping("/api/admin/application-periods")
public class ApplicationPeriodController {

    private final ApplicationPeriodService service;

    public ApplicationPeriodController(ApplicationPeriodService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create or Update an application period",
            description = "Defines when students can start and end their applications for a specific academic year."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Period successfully created/updated",
            content = @Content(schema = @Schema(implementation = PeriodRequestDTO.class))
    )
    @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
    @PostMapping
    public ResponseEntity<?> createPeriod(@RequestBody PeriodRequestDTO dto) {
        return ResponseEntity.ok(service.createOrUpdate(dto));
    }
}