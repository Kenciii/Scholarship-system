package com.example.stipendije.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for user authentication")
public class LoginRequestDTO {

    @Schema(
            description = "User's registered email address",
            example = "student@unsa.ba",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "User's password",
            example = "Password123!",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    private String password;
}