package com.eukolos.companycase.dto;

import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record EmployeeRecursionDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        double salary,
        LocalDate hireDate,
        Department department
) {

    public static EmployeeRecursionDto toDto(Employee employee) {
        return new EmployeeRecursionDto(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getSalary(),
                employee.getHireDate(),
                employee.getDepartment()
        );
    }

    public static List<EmployeeRecursionDto> toDtoList(List<Employee> employeeList) {
        return Optional.of(employeeList.stream().map(EmployeeRecursionDto::toDto).toList()).orElse(new ArrayList<>());
    }

    public static Employee toEntity(EmployeeRecursionDto employeeDto) {
        return Employee.builder()
                .firstName(employeeDto.firstName)
                .lastName(employeeDto.lastName)
                .email(employeeDto.email)
                .phoneNumber(employeeDto.phoneNumber)
                .salary(employeeDto.salary)
                .hireDate(employeeDto.hireDate)
                .department(employeeDto.department)
                .build();
    }

    public static List<Employee> toEntityList(List<EmployeeRecursionDto> employeeDtoList) {
        return Optional.of(employeeDtoList.stream().map(EmployeeRecursionDto::toEntity).toList()).orElse(new ArrayList<>());
    }
}
