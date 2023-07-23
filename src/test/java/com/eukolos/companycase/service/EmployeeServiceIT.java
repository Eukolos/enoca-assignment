package com.eukolos.companycase.service;

import com.eukolos.companycase.CompanyCaseApplication;
import com.eukolos.companycase.abstraction.ITAbstract;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompanyCaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class EmployeeServiceIT extends ITAbstract {
    @Autowired
    private EmployeeRepository repository;

    @Test
    public void When_HireEmployee_Expect_ReturnNewEmployee(){
        repository.save(new Employee(
                null,
                "emin",
                "aksoy",
                "eminaksoy@hotmail.com",
                "+12 000 000 00 00",
                1200.00,
                null,
                Department.ACCOUNTING,
                null
        ));

        Employee fromDb = repository.findEmployeeByEmail("eminaksoy@hotmail.com").orElseThrow();

        Assertions.assertEquals("emin", fromDb.getFirstName());

    }


}
