package ru.cathel.saaserpcore.auth.founder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.cathel.saaserpcore.auth.constant.AuthRequestValidationConstants;

public record FounderSignupRequestDto(
        @NotBlank
        @Size(min = 6, max = 255)
        @Pattern(regexp = AuthRequestValidationConstants.EMAIL_REGEX_PATTERN)
        String email,
        @NotBlank
        @Size(min = 8, max = 64)
        @Pattern(regexp = AuthRequestValidationConstants.PASSWORD_REGEX_PATTERN)
        String password) {
}
