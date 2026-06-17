package ru.cathel.saaserpcore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;

public record FounderRefreshTokenClaims(Integer id, String tokenType) {
    public FounderRefreshTokenClaims {
        if (id == null || tokenType == null) {
            throw new InvalidJwtTokenException("Missing required claims");
        }
    }
}
