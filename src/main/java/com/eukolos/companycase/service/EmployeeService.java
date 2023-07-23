package com.eukolos.companycase.service;

import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.exception.AlreadyExistInCompanyException;
import com.eukolos.companycase.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository; // only one repository should be here Single Responsibility
    private final CompanyService companyService;

    public EmployeeDto hireEmployee(EmployeeCreateRequest request) {
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

    public List<EmployeeDto> getEmployeeListByCompany(Long companyId) {
        return EmployeeDto.toDtoList(repository.findEmployeeByCompany_Id(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+companyId+" not founded")));
    }

    public List<EmployeeDto> getEmployeeByCompanyAndDepartment(String companyName, Department department){
        return EmployeeDto.toDtoList(
                repository.findEmployeeByCompanyCompanyNameAndDepartment(companyName,department)
                        .orElseThrow(() -> new EntityNotFoundException("Employees with "+companyName+" Company and "+ department +"  department not founded"))
        );
    }

    public EmployeeDto getEmployeeById(Long id) {
        return EmployeeDto.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+id+" not founded")));
    }

    public EmployeeDto updateEmployeeById(Long id, EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+id+" not founded"));
        return EmployeeDto.toDto(
                repository.save(
                        Employee.builder()
                                .id(employee.getId())
                                .firstName(employeeCreateRequest.firstName())
                                .lastName(employeeCreateRequest.lastName())
                                .email(employeeCreateRequest.email())
                                .salary(employeeCreateRequest.salary())
                                .phoneNumber(employeeCreateRequest.phoneNumber())
                                .hireDate(employee.getHireDate())
                                .department(employeeCreateRequest.department())
                                .company(employee.getCompany())
                                .build()
                )
        );
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }

    public EmployeeDto transferEmployee(Long id, String companyName){
        Company company = companyService.getCompanyByName(companyName);
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID "+id+" not founded"));
        if(employee.getCompany().getId().equals(company.getId())) {
            throw new AlreadyExistInCompanyException("The Worker " + employee.getFirstName() + " is already employed at " +employee.getCompany().getCompanyName()+" Company" );
        }
        return EmployeeDto.toDto(
                repository.save(
                        Employee.builder()
                                .id(employee.getId())
                                .firstName(employee.getFirstName())
                                .lastName(employee.getLastName())
                                .email(employee.getEmail())
                                .salary(employee.getSalary())
                                .phoneNumber(employee.getPhoneNumber())
                                .hireDate(employee.getHireDate())
                                .department(employee.getDepartment())
                                .company(company)
                                .build()
                )
        );
    }

}
