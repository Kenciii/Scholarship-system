package com.example.stipendije.controller;

import com.example.stipendije.dto.ApplicationRankingDTO;
import com.example.stipendije.service.ApplicationRankingService;
import com.example.stipendije.service.report.PdfReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Reports", description = "Endpoints for generating and exporting scholarship reports")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ApplicationRankingService applicationRankingService;
    private final PdfReportService pdfReportService;

    public ReportController(ApplicationRankingService applicationRankingService, PdfReportService pdfReportService) {
        this.applicationRankingService = applicationRankingService;
        this.pdfReportService = pdfReportService;
    }

    @Operation(
            summary = "Export ranking list as PDF",
            description = "Generates a PDF document containing the student ranking for a specific academic year. Restricted to Admin users."
    )
    @ApiResponse(
            responseCode = "200",
            description = "PDF report successfully generated",
            content = @Content(mediaType = "application/pdf")
    )
    @ApiResponse(responseCode = "403", description = "Forbidden - Admin access required")
    @ApiResponse(responseCode = "404", description = "Ranking for specified year not found")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ranking/{academicYear}/pdf")
    public ResponseEntity<byte[]> exportRankingPdf(
            @Parameter(description = "Academic year in format YYYY-YYYY", example = "2025-2026")
            @PathVariable String academicYear) {

        List<ApplicationRankingDTO> ranking = applicationRankingService.getRankingForYear(academicYear);

        byte[] pdf = pdfReportService.generateRankingPdf(academicYear, ranking);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=ranking-" + academicYear + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}