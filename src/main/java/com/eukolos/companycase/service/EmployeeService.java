package com.eukolos.companycase.service;

import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository; // only one repository should be here Single Responsibility

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return EmployeeDto.toDto(
                repository.save(
                        EmployeeDto.toEntity(employeeDto)
                )
        );
    }

    // not recommended
    // https://vladmihalcea.com/spring-data-findall-anti-pattern/
    public List<EmployeeDto> getEmployeeList() {
        return EmployeeDto.toDtoList(repository.findAll());
    }

    public List<EmployeeDto> getEmployeeByCompany(Long companyId) {
        return EmployeeDto.toDtoList(repository.findEmployeeByCompany_Id(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID {} not founded" + companyId)));
    }
}
