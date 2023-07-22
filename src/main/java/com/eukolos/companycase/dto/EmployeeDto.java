package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public record EmployeeDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        double salary,
        LocalDate hireDate,
        String department,
        CompanyDto companyDto
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
                CompanyDto.toDto(employee.getCompany())
        );
    }

    public static List<EmployeeDto> toDtoList(List<Employee> employeeList) {
        return employeeList.stream().map(EmployeeDto::toDto).toList();
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
                .company(CompanyDto.toEntity(employeeDto.companyDto))
                .build();
    }

    public static List<Employee> toEntityList(List<EmployeeDto> employeeDtoList) {
        return employeeDtoList.stream().map(EmployeeDto::toEntity).toList();
    }
}
