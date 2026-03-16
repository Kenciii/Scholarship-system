package com.example.stipendije.dto;

import com.example.stipendije.model.enums.AchievementType;
import com.example.stipendije.model.enums.LocationType;
import com.example.stipendije.model.enums.SocialStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(description = "Request object for submitting student's personal and academic information for scoring")
public class StudentRequestDTO {

    @Schema(description = "Student's first name", example = "Amer", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Schema(description = "Student's last name", example = "Hadić", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Schema(description = "Contact email address", example = "amer.hadic@student.unsa.ba", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "Grade Point Average (GPA)", example = "9.45", minimum = "6.0", maximum = "10.0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "GPA is required")
    @DecimalMin(value = "6.0", message = "Minimum GPA is 6.0")
    @DecimalMax(value = "10.0", message = "Maximum GPA is 10.0")
    private Double gpa;

    @Schema(description = "Average monthly income per household member", example = "450.0", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Income per member is required")
    @Min(value = 0, message = "Income cannot be negative")
    private Double incomePerMember;

    @Schema(description = "Social status category for extra points", example = "WITHOUT_BOTH_PARENTS", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Social status is required")
    private SocialStatus socialStatus;

    @Schema(description = "Type of residence location", example = "URBAN", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Location type is required")
    private LocationType locationType;

    @Schema(description = "Highest level of achievement reached", example = "INTERNATIONAL", nullable = true)
    private AchievementType achievementType;
}