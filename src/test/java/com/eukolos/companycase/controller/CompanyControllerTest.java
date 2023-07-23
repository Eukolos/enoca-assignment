package com.eukolos.companycase.controller;

import com.eukolos.companycase.dto.*;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.service.CompanyService;
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
public class CompanyControllerTest {
    @MockBean
    private CompanyService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void itShouldCreateCompany_WhenRequestWithCompanyCreateRequest() throws Exception {
        //given
        CompanyCreateRequest request = new CompanyCreateRequest(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );
        CompanyDto response = new CompanyDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );
        //when
        when(service.saveCompany(request)).thenReturn(response);


        mockMvc.perform(post("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.email").value(response.email()))
                .andExpect(status().isCreated());

    }

    @Test
    void itShouldGetAllCompany_WhenRequest() throws Exception {
        CompanyDto c1 = new CompanyDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        CompanyDto c2 = new CompanyDto(
                "Deneme",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        List<CompanyDto> response = new ArrayList<>();
        response.add(c1);
        response.add(c2);
        //when
        when(service.getAllCompanies()).thenReturn(response);

        mockMvc.perform(get("/api/v1/company")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].companyName").value("Deneme"))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetCompany_WhenRequestById() throws Exception {
        CompanyDto response = new CompanyDto(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );


        //when
        when(service.getCompanyById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/v1/company/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("companyName").value(response.companyName()))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldGetCompany_WhenRequestByName() throws Exception {
        Company response = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );


        //when
        when(service.getCompanyByName(response.getCompanyName())).thenReturn(response);

        mockMvc.perform(get("/api/v1/company/name/"+response.getCompanyName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("companyName").value(response.getCompanyName()))
                .andExpect(status().isOk());
    }

    @Test
    void itShouldUpdateCompant_WhenRequestByIdAndRequestBody() throws Exception {
        CompanyCreateRequest request = new CompanyCreateRequest(
                "DENEME",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );
        CompanyDto response = new CompanyDto(
                "DENEME",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );
        //when
        when(service.updateCompanyById(1L,request)).thenReturn(response);


        mockMvc.perform(put("/api/v1/company/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializeJson(request)))
                .andDo(print())
                .andExpect(jsonPath("$.companyName").value(response.companyName()))
                .andExpect(status().isOk());
    }



    private String serializeJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
