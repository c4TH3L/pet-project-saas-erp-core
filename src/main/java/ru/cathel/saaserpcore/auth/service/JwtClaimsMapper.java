package ru.cathel.saaserpcore.auth.service;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtClaimsMapper<T> {
    Claims getClaimsFromBody(T body);
    T parseBodyFromClaims(Claims claims);
}
