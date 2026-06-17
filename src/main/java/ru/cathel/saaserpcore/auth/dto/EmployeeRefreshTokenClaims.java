package ru.cathel.saaserpcore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;

public record EmployeeRefreshTokenClaims(Integer id,
                                         String tokenType,
                                         Integer companyId,
                                         Integer subCompanyId,
                                         Integer roleId) {
    public EmployeeRefreshTokenClaims {
        if (id == null || companyId == null || roleId == null || tokenType == null) {
            throw new InvalidJwtTokenException("Missing required claims");
        }
    }
}
