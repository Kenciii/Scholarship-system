package com.example.stipendije.dto.auth;

import com.example.stipendije.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for new user registration")
public class RegisterRequestDTO {

    @Schema(
            description = "User's unique email address",
            example = "novi.student@unsa.ba",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(
            description = "User password (security policy: min 8 characters)",
            example = "Sifra123!",
            minLength = 8,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    @Schema(
            description = "Role assigned to the user",
            example = "STUDENT",
            allowableValues = {"STUDENT", "ADMIN"}
    )
    private Role role;
}