package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record EmployeeDto(
        @NotNull String firstName,
        @NotNull  String lastName,
        @Email String email,
        @Max(20) @NotNull String phoneNumber,
        @NotNull double salary,
        LocalDate hireDate,
        @NotNull Department department,
        CompanyRecursionDto companyDto
) {

    public static EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getSalary(),
                employee.getHireDate(),
                employee.getDepartment(),
                CompanyRecursionDto.toDto(employee.getCompany())
        );
    }

    public static List<EmployeeDto> toDtoList(List<Employee> employeeList) {
        return Optional.of(employeeList.stream().map(EmployeeDto::toDto).toList()).orElse(new ArrayList<>());
    }

    public static Employee toEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .firstName(employeeDto.firstName)
                .lastName(employeeDto.lastName)
                .email(employeeDto.email)
                .phoneNumber(employeeDto.phoneNumber)
                .salary(employeeDto.salary)
                .hireDate(employeeDto.hireDate)
                .department(employeeDto.department)
                .company(CompanyRecursionDto.toEntity(employeeDto.companyDto))
                .build();
    }

    public static List<Employee> toEntityList(List<EmployeeDto> employeeDtoList) {
        return Optional.of(employeeDtoList.stream().map(EmployeeDto::toEntity).toList()).orElse(new ArrayList<>());
    }
}
