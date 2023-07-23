package com.eukolos.companycase.service;


import com.eukolos.companycase.dto.EmployeeCreateRequest;
import com.eukolos.companycase.dto.EmployeeDto;
import com.eukolos.companycase.dto.EmployeeUpdateRequest;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository repository;
    @Mock
    private CompanyService companyService;
    @InjectMocks
    private EmployeeService service;

    @Test
    void shouldReturnEmployeeDtoWhenHiredEmployee() {
        //given
        EmployeeCreateRequest request = new EmployeeCreateRequest(
                "enoca",
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                Department.ACCOUNTING
        );
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Employee responseFromDb = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        //when
        when(repository.save(any(Employee.class))).thenReturn(responseFromDb);

        EmployeeDto response = service.hireEmployee(request);
        assertEquals("emin", response.firstName());
        assertEquals("emin@aksoy.com", response.email());
    }

    @Test
    void shouldReturnEmployeeDtoListWhenFindAllEmployee() {
        //given
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Employee employee1 = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );
        Employee employee2 = new Employee(
                2L,
                "Ali",
                "veli",
                "ali@veli.com",
                "123 123 12 12",
                30000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        //when
        when(repository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> response = service.getAllEmployees();
        assertEquals(2, response.size());
        assertEquals("emin@aksoy.com", response.get(0).email());
        assertEquals("Ali", response.get(1).firstName());
    }

    @Test
    void shouldReturnEmployeeDtoListWhenFindAllEmployeeWithCompanyId() {
        //given
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Employee employee1 = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );
        Employee employee2 = new Employee(
                2L,
                "Ali",
                "veli",
                "ali@veli.com",
                "123 123 12 12",
                30000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        List<Employee> memberList = new ArrayList<>();
        memberList.add(employee1);
        memberList.add(employee2);
        //when
        when(repository.findEmployeeByCompany_Id(1L)).thenReturn(Optional.of(memberList));

        List<EmployeeDto> response = service.getEmployeeListByCompany(1L);
        assertEquals(2, response.size());
        assertEquals("emin@aksoy.com", response.get(0).email());
        assertEquals("Ali", response.get(1).firstName());
    }

    @Test
    void shouldReturnEmployeeDtoListWhenFindAllEmployeeWithCompanyIdAndDepartment() {
        //given
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Employee employee1 = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );
        Employee employee2 = new Employee(
                2L,
                "Ali",
                "veli",
                "ali@veli.com",
                "123 123 12 12",
                30000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        List<Employee> memberList = new ArrayList<>();
        memberList.add(employee1);
        memberList.add(employee2);
        //when
        when(repository.findEmployeeByCompanyCompanyNameAndDepartment("enoca",Department.ACCOUNTING)).thenReturn(Optional.of(memberList));

        List<EmployeeDto> response = service.getEmployeeByCompanyAndDepartment("enoca",Department.ACCOUNTING);
        assertEquals(2, response.size());
        assertEquals("emin@aksoy.com", response.get(0).email());
        assertEquals("Ali", response.get(1).firstName());
    }

    @Test
    void shouldReturnEmployeeDtoWhenFindById() {
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Employee employee = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        Long id = 1L;

        //when
        when(repository.findById(id)).thenReturn(Optional.of(employee));

        EmployeeDto response = service.getEmployeeById(id);
        assertEquals("aksoy", response.lastName());
        assertEquals("emin@aksoy.com", response.email());

    }

    @Test
    void shouldReturnUpdatedEmployeeDtoWhenRequestByIdAndBody() {

        Company company = new Company(
                2L,
                "ipragaz",
                "Karabük",
                "contact@ipragaz.com",
                "+90 123 221 73 54",
                new ArrayList<>()
        );

        Employee employee = new Employee(
                1L,
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );

        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest(
                "Veli",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                Department.ACCOUNTING
        );

        Employee updatedEmployee = new Employee(
                1L,
                "Veli",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                company
        );




        //when
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenReturn(updatedEmployee);


        EmployeeDto response = service.updateEmployeeById(1L, employeeUpdateRequest);
        assertEquals("Veli", response.firstName());

    }

//    @Test
//    void shouldReturnTransferedEmployeeDtoWhenRequestByIdAndCompanyName() {
//
//
//        Company newCompany = new Company(
//                1L,
//                "ipragaz",
//                "Karabük",
//                "contact@ipragaz.com",
//                "+90 123 221 73 54",
//                new ArrayList<>()
//        );
//        Employee employee = new Employee(
//                1L,
//                "emin",
//                "aksoy",
//                "emin@aksoy.com",
//                "123 123 12 12",
//                20000.00,
//                LocalDate.now(),
//                Department.ACCOUNTING,
//                newCompany
//        );
//        Company company = new Company(
//                2L,
//                "enoca",
//                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
//                "contact@enoca.com",
//                "+90 850 221 73 54",
//                new ArrayList<>()
//        );
//        Employee transferedEmployee = new Employee(
//                2L,
//                "emin",
//                "aksoy",
//                "emin@aksoy.com",
//                "123 123 12 12",
//                20000.00,
//                LocalDate.now(),
//                Department.ACCOUNTING,
//                company
//        );
//
//
//        //when
//        when(companyService.getCompanyByName(newCompany.getCompanyName())).thenReturn(newCompany);
//        when(repository.findById(1L)).thenReturn(Optional.of(employee));
//        when(repository.save(any(Employee.class))).thenReturn(transferedEmployee);
//
//
//        EmployeeDto response = service.transferEmployee(1L, "ipragaz");
//        assertEquals("ipragaz", response.companyDto().companyName());
//
//    }

}
