package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeCreateRequest request) {
        return new ResponseEntity<>(service.saveEmployee(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployeeList() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeeList(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeByCompany(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(service.updateEmployeeById(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

}
