package com.example.stipendije.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Request object for creating or updating a scholarship application period")
public class PeriodRequestDTO {

    @Schema(description = "The academic year identifier", example = "2025-2026")
    private String academicYear;

    @Schema(description = "The date when the application period opens", example = "2025-09-01")
    private LocalDate startDate;

    @Schema(description = "The date when the application period closes", example = "2025-10-31")
    private LocalDate endDate;
}