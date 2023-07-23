package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record EmployeeCreateRequest(
        @NotNull String companyName,
        @NotNull String firstName,
        @NotNull String lastName,
        @Email String email,
        @NotNull String phoneNumber,
        double salary,
        Department department
) {
}
