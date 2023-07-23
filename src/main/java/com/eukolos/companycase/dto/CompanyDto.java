package com.eukolos.companycase.dto;


import com.eukolos.companycase.entity.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CompanyDto(
        @NotNull String companyName,
        @NotNull String address,
        @Email String email,
        @NotNull  String phoneNumber,
        List<EmployeeRecursionDto> employeesDto
) {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto(
                company.getCompanyName(),
                company.getAddress(),
                company.getEmail(),
                company.getPhoneNumber(),
                Optional.of(EmployeeRecursionDto.toDtoList(company.getEmployees())).orElse(new ArrayList<>())
        );
    }

    public static List<CompanyDto> toDtoList(List<Company> companyList) {

        return Optional.of(companyList.stream().map(CompanyDto::toDto).toList()).orElse(new ArrayList<>());
    }

    public static Company toEntity(CompanyDto companyDto) {
        return Company.builder()
                .companyName(companyDto.companyName)
                .address(companyDto.address)
                .email(companyDto.email)
                .phoneNumber(companyDto.phoneNumber)
                .employees(Optional.of(EmployeeRecursionDto.toEntityList(companyDto.employeesDto)).orElse(new ArrayList<>()))
                .build();
    }

    public static List<Company> toEntityList(List<CompanyDto> companyDtoList) {
        return companyDtoList.stream().map(CompanyDto::toEntity).toList();
    }
}
