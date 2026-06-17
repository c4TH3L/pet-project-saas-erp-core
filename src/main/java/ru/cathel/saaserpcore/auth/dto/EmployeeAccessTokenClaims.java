package ru.cathel.saaserpcore.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;

public record EmployeeAccessTokenClaims(Integer id,
                                        String tokenType,
                                        String email,
                                        String companyCode,
                                        Integer companyId,
                                        Integer subCompanyId,
                                        Integer roleId,
                                        String roleName) {
    public EmployeeAccessTokenClaims {
        if (id == null || companyId == null || email == null || roleId == null || roleName == null || tokenType == null) {
            throw new InvalidJwtTokenException("Missing required claims");
        }
    }
}
