package com.eukolos.companycase.dto;


import com.eukolos.companycase.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;


public record CompanyCreateRequest(
        @NotNull String companyName,
        @NotNull String address,
        @Email String email,
        @NotNull  String phoneNumber
) {
    public static Company toEntity(CompanyCreateRequest request) {
        return Company.builder()
                .companyName(request.companyName)
                .address(request.address)
                .email(request.email)
                .phoneNumber(request.phoneNumber)
                .employees(new ArrayList<>())
                .build();
    }
}
