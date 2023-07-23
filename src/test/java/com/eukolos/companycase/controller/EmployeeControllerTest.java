package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.*;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class EmployeeControllerTest {
    @MockBean
    private EmployeeService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldHireEmployee_WhenEmployeeRequestBodyWithCompanyName() throws Exception {
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
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
                );


        EmployeeDto employeeDto = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
                );
        //when
        when(service.hireEmployee(request)).thenReturn(employeeDto);


        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.email").value(request.email()))
                .andExpect(status().isCreated());
    }



    @Test
    void itShouldGetAllEmployee_WhenRequest() throws Exception {
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );

        EmployeeDto e1 = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );
        EmployeeDto e2 = new EmployeeDto(
                "ali",
                "Veli",
                "ali@veli.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );

        List<EmployeeDto> response = new ArrayList<>();
        response.add(e1);
        response.add(e2);
        //when
        when(service.getAllEmployees()).thenReturn(response);

        mockMvc.perform(get("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].firstName").value("ali"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetSpecificCompaniesAllEmployees_WhenRequestWithCompanyId() throws Exception {
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );

        EmployeeDto e1 = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );
        EmployeeDto e2 = new EmployeeDto(
                "ali",
                "Veli",
                "ali@veli.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );

        List<EmployeeDto> response = new ArrayList<>();
        response.add(e1);
        response.add(e2);
        //when
        when(service.getEmployeeListByCompany(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/employee/company/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("emin"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetSpecificCompanyAndItsDepartmentsAllEmployees_WhenRequestWithCompanyNameAndDepartmentName() throws Exception {
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );

        EmployeeDto e1 = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );
        EmployeeDto e2 = new EmployeeDto(
                "ali",
                "Veli",
                "ali@veli.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );

        List<EmployeeDto> response = new ArrayList<>();
        response.add(e1);
        response.add(e2);
        //when
        when(service.getEmployeeByCompanyAndDepartment("enoca",Department.ACCOUNTING)).thenReturn(response);

        mockMvc.perform(get("/api/v1/employee/department")
                        .param("companyName",companyRecursionDto.companyName())
                        .param("department", Department.ACCOUNTING.name())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("emin"))
                .andExpect(status().isOk());
    }
    @Test
    void itShouldGetEmployee_WhenRequestById() throws Exception {
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );

        EmployeeDto employeeDto = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );

        //when
        when(service.getEmployeeById(1L)).thenReturn(employeeDto);

        mockMvc.perform(get("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("firstName").value("emin"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldUpdateEmployee_WhenRequestByIdAndRequestBody() throws Exception {
        EmployeeUpdateRequest request = new EmployeeUpdateRequest(
                "ali",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                Department.ACCOUNTING

        );
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );


        EmployeeDto employeeDto = new EmployeeDto(
                "ali",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );
        //when
        when(service.updateEmployeeById(1L,request)).thenReturn(employeeDto);


        mockMvc.perform(put("/api/v1/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.firstName").value(request.firstName()))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldTransferEmployeeToAnotherCompany_WhenRequestWithEmployeeIdAndCompanyName() throws Exception {
        CompanyRecursionDto companyRecursionDto = new CompanyRecursionDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );

        EmployeeDto response = new EmployeeDto(
                "emin",
                "aksoy",
                "emin@aksoy.com",
                "123 123 12 12",
                20000.00,
                LocalDate.now(),
                Department.ACCOUNTING,
                companyRecursionDto
        );
        //when
        when(service.transferEmployee(1L,companyRecursionDto.companyName())).thenReturn(response);

        mockMvc.perform(put("/api/v1/employee/transfer")
                        .param("companyName",companyRecursionDto.companyName())
                        .param("employeeId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.companyDto.companyName").value("enoca"))
                .andExpect(status().isOk());
    }
    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
