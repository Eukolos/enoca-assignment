package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
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
    public EmployeeDto saveEmployee(@RequestBody EmployeeCreateRequest request) {
        return service.saveEmployee(request);
    }

    @GetMapping
    public List<EmployeeDto> getEmployeeList() {
        return service.getAllEmployees();
    }

    @GetMapping("/company/{id}")
    public List<EmployeeDto> getEmployeeList(@PathVariable Long id) {
        return service.getEmployeeByCompany(id);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployeeById(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {
        return service.updateEmployeeById(id, employeeDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
    }

}
