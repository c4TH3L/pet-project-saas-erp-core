package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import ru.cathel.saaserpcore.auth.constant.JwtClaimsField;
import ru.cathel.saaserpcore.auth.dto.FounderAccessTokenClaims;

public class FounderAccessTokenClaimsMapper extends AbstractJwtClaimsMapper<FounderAccessTokenClaims> {
    @Override
    protected void populateClaims(Claims claims, FounderAccessTokenClaims body) {
        claims.put(JwtClaimsField.ID.getFieldName(), body.id());
        claims.put(JwtClaimsField.TOKEN_TYPE.getFieldName(), body.tokenType().name());
        claims.put(JwtClaimsField.EMAIL.getFieldName(), body.email());
    }

    @Override
    protected FounderAccessTokenClaims resolveClaims(Claims claims) {
        return new FounderAccessTokenClaims(
                claims.get(JwtClaimsField.ID.getFieldName(), Integer.class),
                JwtUtils.safeParseStringToJwtTokenType(claims.get(JwtClaimsField.TOKEN_TYPE.getFieldName(), String.class)),
                claims.get(JwtClaimsField.EMAIL.getFieldName(), String.class)
        );
    }
}
