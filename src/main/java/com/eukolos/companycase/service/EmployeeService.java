package com.eukolos.companycase.service;

import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository; // only one repository should be here Single Responsibility
    private final CompanyService companyService;

    public EmployeeDto saveEmployee(EmployeeCreateRequest request) {
        return EmployeeDto.toDto(
                repository.save(
                        Employee.builder()
                                .firstName(request.firstName())
                                .lastName(request.lastName())
                                .phoneNumber(request.phoneNumber())
                                .email(request.email())
                                .department(request.department())
                                .company(companyService.getCompanyByName(request.companyName()))
                                .build()
                )
        );
    }

    // not recommended
    // https://vladmihalcea.com/spring-data-findall-anti-pattern/
    public List<EmployeeDto> getAllEmployees() {
        return EmployeeDto.toDtoList(repository.findAll());
    }

    public List<EmployeeDto> getEmployeeByCompany(Long companyId) {
        return EmployeeDto.toDtoList(repository.findEmployeeByCompany_Id(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+companyId+" not founded")));
    }

    //todo getEmployeeByCompanyAndDepartment

    public EmployeeDto getEmployeeById(Long id) {
        return EmployeeDto.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+id+" not founded")));
    }

    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+id+" not founded"));
        return EmployeeDto.toDto(
                repository.save(
                        Employee.builder()
                                .id(employee.getId())
                                .firstName(employeeDto.firstName())
                                .lastName(employeeDto.lastName())
                                .email(employeeDto.email())
                                .phoneNumber(employeeDto.phoneNumber())
                                .department(employeeDto.department())
                                .company(employee.getCompany())
                                .build()
                )
        );
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

}
