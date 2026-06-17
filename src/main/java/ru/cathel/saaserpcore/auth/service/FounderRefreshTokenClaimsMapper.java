package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import ru.cathel.saaserpcore.auth.constant.JwtClaimsField;
import ru.cathel.saaserpcore.auth.dto.FounderRefreshTokenClaims;

public class FounderRefreshTokenClaimsMapper extends AbstractJwtClaimsMapper<FounderRefreshTokenClaims> {
    @Override
    protected void populateClaims(Claims claims, FounderRefreshTokenClaims body) {
        claims.put(JwtClaimsField.ID.getFieldName(), body.id());
        claims.put(JwtClaimsField.TOKEN_TYPE.getFieldName(), body.tokenType().name());
    }

    @Override
    protected FounderRefreshTokenClaims resolveClaims(Claims claims) {
        return new FounderRefreshTokenClaims(
                claims.get(JwtClaimsField.ID.getFieldName(), Integer.class),
                JwtUtils.safeParseStringToJwtTokenType(claims.get(JwtClaimsField.TOKEN_TYPE.getFieldName(), String.class))
        );
    }
}
