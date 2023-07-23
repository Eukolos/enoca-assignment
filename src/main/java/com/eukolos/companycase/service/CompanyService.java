package com.eukolos.companycase.service;

import com.eukolos.companycase.dto.CompanyDto;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyDto saveCompany(CompanyDto companyDto) {
        return CompanyDto.toDto(repository.save(CompanyDto.toEntity(companyDto)));
    }

    // not recommended
    // https://vladmihalcea.com/spring-data-findall-anti-pattern/
    public List<CompanyDto> getAllCompanies() {
        log.warn(repository.findAll().toString());
        return CompanyDto.toDtoList(repository.findAll());
    }

    public CompanyDto getCompanyById(Long id) {
        return CompanyDto.toDto(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID "+id+" not founded")));
    }

    public Company getCompanyByName(String companyName) {
        return repository.findCompanyByCompanyName(companyName)
                .orElseThrow(() -> new EntityNotFoundException("Company with name "+companyName+" not founded"));
    }

    public CompanyDto updateCompanyById(Long id, CompanyDto companyDto) {
        Company company = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with ID "+id+" not founded"));
        return CompanyDto.toDto(
                repository.save(
                        Company.builder()
                                .id(company.getId())
                                .companyName(companyDto.companyName())
                                .address(companyDto.address())
                                .email(companyDto.email())
                                .phoneNumber(companyDto.phoneNumber())
                                .employees(company.getEmployees())
                                .build()
                )
        );
    }

    public void deleteCompanyById(Long id) {
        repository.deleteById(id);
    }


}
