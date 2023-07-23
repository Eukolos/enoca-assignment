package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;


public record EmployeeUpdateRequest(
        @NotNull String firstName,
        @NotNull String lastName,
        @Email String email,
        @Max(20) @NotNull String phoneNumber,
        @NotNull double salary,
        @NotNull Department department
) {
}
