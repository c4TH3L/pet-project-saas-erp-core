package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import ru.cathel.saaserpcore.auth.constant.JwtClaimsField;
import ru.cathel.saaserpcore.auth.dto.EmployeeRefreshTokenClaims;

public class EmployeeRefreshTokenClaimsMapper extends AbstractJwtClaimsMapper<EmployeeRefreshTokenClaims> {
    @Override
    protected void populateClaims(Claims claims, EmployeeRefreshTokenClaims body) {
        claims.put(JwtClaimsField.ID.getFieldName(), body.id());
        claims.put(JwtClaimsField.TOKEN_TYPE.getFieldName(), body.tokenType().name());
        claims.put(JwtClaimsField.COMPANY_ID.getFieldName(), body.companyId());
        claims.put(JwtClaimsField.SUB_COMPANY_ID.getFieldName(), body.subCompanyId());
        claims.put(JwtClaimsField.ROLE_ID.getFieldName(), body.roleId());
    }

    @Override
    protected EmployeeRefreshTokenClaims resolveClaims(Claims claims) {
        return new EmployeeRefreshTokenClaims(
                claims.get(JwtClaimsField.ID.getFieldName(), Integer.class),
                JwtUtils.safeParseStringToJwtTokenType(claims.get(JwtClaimsField.TOKEN_TYPE.getFieldName(), String.class)),
                claims.get(JwtClaimsField.COMPANY_ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.SUB_COMPANY_ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.ROLE_ID.getFieldName(), Integer.class)
        );
    }
}
