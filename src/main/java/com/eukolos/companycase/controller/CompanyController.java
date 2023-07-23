package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.CompanyCreateRequest;
import com.eukolos.companycase.dto.CompanyDto;
import com.eukolos.companycase.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    // Not needed ResponseEntity cause handled thanx to ResponseBody from RestController
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto saveCompany(@RequestBody @Valid CompanyCreateRequest companyCreateRequest) {
        return service.saveCompany(companyCreateRequest);
    }

    @GetMapping
    public List<CompanyDto> getCompanyList() {
        return service.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        return service.getCompanyById(id);
    }

    @GetMapping("/name/{companyName}")
    public CompanyDto getCompanyByName(@PathVariable String companyName) {
        return CompanyDto.toDto(service.getCompanyByName(companyName));
    }

    @PutMapping("/{id}")
    public CompanyDto updateCompanyById(@PathVariable Long id, @RequestBody @Valid CompanyCreateRequest companyCreateRequest) {
        return service.updateCompanyById(id, companyCreateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        service.deleteCompanyById(id);
    }

}
