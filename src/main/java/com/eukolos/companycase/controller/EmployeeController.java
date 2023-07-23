package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service; // Single Responsibility

    // Not needed ResponseEntity cause handled thanx to ResponseBody from RestController
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto hireEmployee(@RequestBody EmployeeCreateRequest request) {
        return service.hireEmployee(request);
    }

    @GetMapping
    public List<EmployeeDto> getEmployeeList() {
        return service.getAllEmployees();
    }

    @GetMapping("/company/{id}")
    public List<EmployeeDto> getEmployeeListByCompany(@PathVariable Long id) {
        return service.getEmployeeListByCompany(id);
    }

    @GetMapping("/department")
    public List<EmployeeDto> getEmployeeByCompanyAndDepartment(
            @RequestParam String companyName,
            @RequestParam Department department
            ) {
        return service.getEmployeeByCompanyAndDepartment(companyName, department);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployeeById(
            @PathVariable Long id,
            @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        return service.updateEmployeeById(id, employeeCreateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
    }

    @PutMapping("/transfer")
    public EmployeeDto transferEmployee(
            @RequestParam String companyName,
            @RequestParam Long employeeId
    ){
        return service.transferEmployee(employeeId,companyName);
    }

}
