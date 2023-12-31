package com.eukolos.companycase.dto;


import com.eukolos.companycase.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CompanyRecursionDto(
        @NotNull String companyName,
        String address,
        @Email String email,
        @NotNull  String phoneNumber
) {
    public static CompanyRecursionDto toDto(Company company) {
        return new CompanyRecursionDto(
                company.getCompanyName(),
                company.getAddress(),
                company.getEmail(),
                company.getPhoneNumber()
        );
    }

    public static List<CompanyRecursionDto> toDtoList(List<Company> companyList) {

        return Optional.of(companyList.stream().map(CompanyRecursionDto::toDto).toList()).orElse(new ArrayList<>());
    }

    public static Company toEntity(CompanyRecursionDto companyDto) {
        return Company.builder()
                .companyName(companyDto.companyName)
                .address(companyDto.address)
                .email(companyDto.email)
                .phoneNumber(companyDto.phoneNumber)
                .build();
    }

    public static List<Company> toEntityList(List<CompanyRecursionDto> companyDtoList) {
        return companyDtoList.stream().map(CompanyRecursionDto::toEntity).toList();
    }
}
