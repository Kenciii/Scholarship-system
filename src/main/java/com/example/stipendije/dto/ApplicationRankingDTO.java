package com.example.stipendije.dto;

import com.example.stipendije.model.enums.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Data transfer object representing a student's position on the ranking list")
public class ApplicationRankingDTO {

    @Schema(description = "Unique ID of the application", example = "101")
    private Long applicationId;

    @Schema(description = "Student's first name", example = "Amar")
    private String firstName;

    @Schema(description = "Student's last name", example = "Hadić")
    private String lastName;

    @Schema(description = "Calculated total points based on GPA, social status, and achievements", example = "92.50")
    private Double totalPoints;

    @Schema(description = "Current rank on the list (1 being the highest)", example = "1")
    private Integer rank;

    @Schema(description = "Status of the application (PENDING, APPROVED, REJECTED)", example = "APPROVED")
    private ApplicationStatus status;

    public ApplicationRankingDTO(Long applicationId,
                                 String firstName,
                                 String lastName,
                                 Double totalPoints,
                                 Integer rank,
                                 ApplicationStatus status) {
        this.applicationId = applicationId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.status = status;
    }
}