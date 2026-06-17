package ru.cathel.saaserpcore.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JwtClaimsField {
    ID("sub"),
    TOKEN_TYPE("token_type"),
    EMAIL("email"),
    COMPANY_CODE("company_code"),
    COMPANY_ID("company_id"),
    SUB_COMPANY_ID("sub_company_id"),
    ROLE_ID("role_id"),
    ROLE_NAME("role_name");

    private final String fieldName;
}
