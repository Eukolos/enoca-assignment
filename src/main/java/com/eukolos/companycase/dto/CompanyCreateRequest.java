package com.eukolos.companycase.dto;


import com.eukolos.companycase.entity.Company;

import java.util.ArrayList;


public record CompanyCreateRequest(
        String companyName,
        String address,
        String email,
        String phoneNumber
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
