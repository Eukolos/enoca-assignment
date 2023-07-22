package com.eukolos.companycase.dto;


import com.eukolos.companycase.entity.Company;
import java.util.List;

public record CompanyDto(
        String companyName,
        String address,
        String email,
        String phoneNumber,
        List<EmployeeDto> employeesDto
) {
    public static CompanyDto toDto(Company company) {
        return new CompanyDto(
                company.getCompanyName(),
                company.getAddress(),
                company.getEmail(),
                company.getPhoneNumber(),
                EmployeeDto.toDtoList(company.getEmployees())
        );
    }

    public static List<CompanyDto> toDtoList(List<Company> companyList) {
        return companyList.stream().map(CompanyDto::toDto).toList();
    }

    public static Company toEntity(CompanyDto companyDto) {
        return Company.builder()
                .companyName(companyDto.companyName)
                .address(companyDto.address)
                .email(companyDto.email)
                .phoneNumber(companyDto.phoneNumber)
                .employees(EmployeeDto.toEntityList(companyDto.employeesDto))
                .build();
    }

    public static List<Company> toEntityList(List<CompanyDto> companyDtoList) {
        return companyDtoList.stream().map(CompanyDto::toEntity).toList();
    }
}
