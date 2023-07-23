package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Department;


public record EmployeeUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        double salary,
        Department department
) {
}
