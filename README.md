# Company Management System - Enoca Assigment
<img src="image/photo.png" alt="feature"/>

[##**Answer of Assigment Questions**##](https://github.com/Eukolos/enoca-assignment/blob/master/ANSWER.md)  

## Table of Contents

- [Dependencies Used](#dependencies-used)
- [Run the App](#🔨-run-the-app)
- [Answer of Assigment Questions](https://github.com/Eukolos/enoca-assignment/blob/master/ANSWER.md)
- [Tree of Layered Structure](#tree-of-layered-structure)
- [Testing](#testing)
- [Test App on Postman](#test-app-on-postman)
- [Contact](#contact)

## Dependencies Used
- [Spring Framework](https://docs.spring.io/spring-framework/docs/current/reference/html/)
    - [Spring Boot](https://spring.io/projects/spring-boot)
    - [Spring Web](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html)
    - [Spring JPA](https://spring.io/projects/spring-data-jpa)
    - [Spring Validation](https://docs.spring.io/spring-framework/docs/4.1.x/spring-framework-reference/html/validation.html)
    - [Spring Test](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html)
    - [TestContainer](https://testcontainers.com/)
    - [Lombok](https://projectlombok.org/)
- [Postgresql](https://www.postgresql.org/)
- [PG Admin 4](https://www.pgadmin.org/docs/)
- [Java JDK 17](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/)


## 🔨 Run the App

#### Maven & Docker

<b>1 )</b> Download your project from this link shown below
```
    git clone https://github.com/Eukolos/enoca-assignment.git
```

<b>2 )</b> Go to the project's home directory shown below
```
    cd enoca-assignment
```
<b>4 )</b> Run the project's db and db ui though this command shown below
```
    docker-compose up
```

<b>3 )</b> Start the project
```
    mvn spring-boot:start
```

## Testing

The project is thoroughly tested to ensure code quality and reliability.

### Integration & Unit Tests

Integration tests have been implemented using [JUnit](https://junit.org/) with TestContainer and [Postman](https://www.postman.com/) for API endpoints. The integration test files are located in the `src/test/java` directory.

Unit tests are written using [Mockito](https://site.mockito.org/) and [JUnit](https://junit.org/). Test cases for individual components and functions can be found in the `src/test/java` directory.

### Test Coverage

[**All my tests and coverage percent in here**](https://raw.githack.com/Eukolos/enoca-assignment/master/htmlReport/index.html)


## Tree of Layered Structure

```txt
.
src
├── controller
│   ├── CompanyController.java class
│   └── EmployeeController.java class
├── data-seed
│   ├── AvansasCompany.java class
│   ├── EnocaCompany.java class
│   ├── GratisCompany.java class
│   └── TurkcellCompany.java class
├── dto
│   ├── CompanyCreateRequest.java record
│   ├── CompanyDto.java record
│   ├── CompanyRecursion.java record
│   ├── EmployeeCreateRequest.java record
│   ├── EmployeeDto.java record
│   ├── EmployeeRecursionDto.java record
│   └── EmployeeUpdateRequest.java record
├── entity
│   ├── Company.java class
│   ├── Department.java enum
│   └── Employee.java class
├── exception
│   ├── AlreadyExistInCompanyException.java class
│   └── GeneralExceptionAdvice.java class
├── repository
│   ├── CompanyRepository.java interface
│   └── EmployeeRepository.java interface
├── service
│   ├── CompanyService.java class
│   └── EmployeeService.java class
│  
└── CompanyCaseApplication.java class
test
├── abstraction
│    └── ITAbsract.java abstract class
├── container
│    └── PostgresTestContainer.java class
├── controller
│   ├── CompanyControllerTest.java class
│   └── EmployeeControllerTest.java class
├── service
│   ├── CompanyServiceIT.java class
│   ├── CompanyServiceTest.java class
│   ├── EmployeeServiceIT.java class
│   └── EmployeeServiceTest.java class
│  
└── CompanyCaseApplicationTests.java class
  
```

## Test App on Postman
- [**Check Endpoints on Postman WebSite**](https://documenter.getpostman.com/view/20436403/2s946mZpXo)
- [**Import Endpoints on your Postman** copy link](https://api.postman.com/collections/20436403-f8c43a7b-9ce8-40ed-a7e8-6a130a18159c?access_key=PMAT-01H61BPSATH41C0QW1DF86AT8S)
- [**Import Endpoints on your Postman** download data](https://github.com/Eukolos/enoca-assignment/tree/master/postman)

<img src="image/postman-ss2.png" alt="feature3"/>
<img src="image/postman-ss.png" alt="feature2"/>

## Contact

This project was created by [Eukolos](https://github.com/Eukolos). Contributions are welcome!

If you have any questions or suggestions, feel free to contact the project maintainer at eminaksoy35@gmail.com.

website: [eminksy.me](https://eminksy.me/)
