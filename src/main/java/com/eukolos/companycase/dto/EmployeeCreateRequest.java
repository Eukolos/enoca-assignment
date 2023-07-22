package com.eukolos.companycase.dto;

import java.time.LocalDate;

public record EmployeeCreateRequest(
        String companyName,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        double salary,
        String department
) {
}
