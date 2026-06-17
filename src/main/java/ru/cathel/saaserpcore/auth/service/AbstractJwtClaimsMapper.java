package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Objects;

public abstract class AbstractJwtClaimsMapper<T> implements JwtClaimsMapper<T> {

    @Override
    public Claims getClaimsFromBody(T body) {
        Objects.requireNonNull(body, "Token body cannot be null");
        Claims claims = Jwts.claims().build();
        populateClaims(claims, body);
        return claims;
    }

    @Override
    public T parseBodyFromClaims(Claims claims) {
        Objects.requireNonNull(claims, "Claims cannot be null");
        return resolveClaims(claims);
    }

    protected abstract void populateClaims(Claims claims, T body);
    protected abstract T resolveClaims(Claims claims);
}
