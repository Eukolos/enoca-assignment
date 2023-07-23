package com.eukolos.companycase.abstraction;

import com.eukolos.companycase.container.PostgresTestContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ITAbstract {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Container
    static PostgreSQLContainer<?> postgresContainer = PostgresTestContainer.getInstance();

}