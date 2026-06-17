package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import ru.cathel.saaserpcore.auth.constant.JwtClaimsField;
import ru.cathel.saaserpcore.auth.dto.EmployeeAccessTokenClaims;

public class EmployeeAccessTokenClaimsMapper extends AbstractJwtClaimsMapper<EmployeeAccessTokenClaims> {
    @Override
    protected void populateClaims(Claims claims, EmployeeAccessTokenClaims body) {
        claims.put(JwtClaimsField.ID.getFieldName(), body.id());
        claims.put(JwtClaimsField.TOKEN_TYPE.getFieldName(), body.tokenType());
        claims.put(JwtClaimsField.EMAIL.getFieldName(), body.email());
        claims.put(JwtClaimsField.COMPANY_CODE.getFieldName(), body.companyCode());
        claims.put(JwtClaimsField.COMPANY_ID.getFieldName(), body.companyId());
        claims.put(JwtClaimsField.SUB_COMPANY_ID.getFieldName(), body.subCompanyId());
        claims.put(JwtClaimsField.ROLE_ID.getFieldName(), body.roleId());
        claims.put(JwtClaimsField.ROLE_NAME.getFieldName(), body.roleName());
    }

    @Override
    protected EmployeeAccessTokenClaims resolveClaims(Claims claims) {
        return new EmployeeAccessTokenClaims(
                claims.get(JwtClaimsField.ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.TOKEN_TYPE.getFieldName(), String.class),
                claims.get(JwtClaimsField.EMAIL.getFieldName(), String.class),
                claims.get(JwtClaimsField.COMPANY_CODE.getFieldName(), String.class),
                claims.get(JwtClaimsField.COMPANY_ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.SUB_COMPANY_ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.ROLE_ID.getFieldName(), Integer.class),
                claims.get(JwtClaimsField.ROLE_NAME.getFieldName(), String.class)
        );
    }
}
