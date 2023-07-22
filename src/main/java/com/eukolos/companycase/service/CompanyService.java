package com.eukolos.companycase.service;

import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService implements CommandLineRunner {
    private final CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(Company.builder().address("sadasdad").build());

    }
}
