package ru.cathel.saaserpcore.auth.employee.dto;

import org.jspecify.annotations.Nullable;

import java.util.List;

public record EmployeePrincipal(int id, int companyId, @Nullable Integer subCompanyId) {
}
