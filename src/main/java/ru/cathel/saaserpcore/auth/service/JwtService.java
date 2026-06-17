package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cathel.saaserpcore.auth.constant.JwtClaimsField;
import ru.cathel.saaserpcore.auth.constant.JwtCreationConstants;
import ru.cathel.saaserpcore.auth.constant.JwtTokenType;
import ru.cathel.saaserpcore.auth.exception.InvalidJwtTokenException;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {
    private final SecretKey secretKey;

    @Autowired
    public JwtService(@Value("${my.jwt.secret}") String rawSecretKey) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawSecretKey));
    }

    public Claims parseClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new InvalidJwtTokenException(e.getMessage());
        }
    }

    public JwtTokenType getTokenTypeFromClaims(Claims claims) {
        String tokenType = claims.get(JwtClaimsField.TOKEN_TYPE.getFieldName(), String.class);
        if(tokenType == null)
            throw new InvalidJwtTokenException("Bad token type");
        return JwtTokenType.fromType(tokenType).orElseThrow(() -> new InvalidJwtTokenException("Invalid token type"));
    }

    public String createAccessTokenFromClaims(Claims claims) {
        return createToken(claims, JwtCreationConstants.ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    public String createRefreshTokenFromClaims(Claims claims) {
        claims.put("jti", UUID.randomUUID().toString());
        return createToken(claims, JwtCreationConstants.REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    private String createToken(Map<String, Object> claims, int validitySeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(validitySeconds)))
                .signWith(secretKey)
                .compact();
    }
}
