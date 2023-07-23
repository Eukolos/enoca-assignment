package com.eukolos.companycase.service;

import com.eukolos.companycase.CompanyCaseApplication;
import com.eukolos.companycase.abstraction.ITAbstract;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.repository.CompanyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompanyCaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CompanyServiceIT extends ITAbstract {
    @Autowired
    private CompanyRepository repository;

    @Test
    public void When_CreateCompany_Expect_ReturnNewCompany(){
        repository.save(new Company(
                null,
                "ipragaz güncel",
                "Karabük",
                "contact@ipragaz.com",
                "+90 123 221 73 54",
                new ArrayList<>()
        ));

        Company fromDb = repository.findCompanyByCompanyName("enoca").orElseThrow();

        Assertions.assertEquals("contact@enoca.com", fromDb.getEmail());

    }


}