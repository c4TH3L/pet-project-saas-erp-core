package ru.cathel.saaserpcore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;

public record FounderAccessTokenClaims(Integer id, String tokenType, String email) {
    public FounderAccessTokenClaims {
        if (id == null || email == null || tokenType == null) {
            throw new InvalidJwtTokenException("Missing required claims");
        }
    }
}
