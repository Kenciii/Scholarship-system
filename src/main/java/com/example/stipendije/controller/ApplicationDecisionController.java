package com.example.stipendije.controller;

import com.example.stipendije.service.ApplicationDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Decisions", description = "Administrative operations for scholarship assignment")
@RestController
@RequestMapping("/api/scholarships")
public class ApplicationDecisionController {

    private final ApplicationDecisionService decisionService;

    public ApplicationDecisionController(ApplicationDecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @Operation(
            summary = "Run scholarship selection process",
            description = "Automatically assigns scholarships based on points and ranking. " +
                    "Status will be set to APPROVED for students within the limit, and REJECTED for others."
    )
    @ApiResponse(responseCode = "200", description = "Decision process completed successfully")
    @ApiResponse(responseCode = "403", description = "Unauthorized - Only admins can run this process")
    @PostMapping("/decide")
    public ResponseEntity<String> decide(
            @Parameter(description = "The academic year for which to process decisions", example = "2025-2026")
            @RequestParam String academicYear,

            @Parameter(description = "Maximum number of scholarships to be approved", example = "50")
            @RequestParam int limit) {

        decisionService.decideScholarships(academicYear, limit);

        return ResponseEntity.ok("Scholarships successfully assigned.");
    }
}