package ru.cathel.saaserpcore.founder.dto;

import java.time.LocalDateTime;

public record FounderResponseDto(int id, String email, LocalDateTime createdAt, boolean emailConfirmed) {
}
