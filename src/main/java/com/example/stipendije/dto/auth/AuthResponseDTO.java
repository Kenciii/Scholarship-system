package com.example.stipendije.dto.auth;

import com.example.stipendije.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Response object containing JWT token and basic user information")
public class AuthResponseDTO {

    @Schema(description = "JWT Bearer token for authenticating subsequent requests",
            example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "User's email address",
            example = "student@unsa.ba")
    private String email;

    @Schema(description = "User's assigned role in the system",
            example = "STUDENT")
    private Role role;
}