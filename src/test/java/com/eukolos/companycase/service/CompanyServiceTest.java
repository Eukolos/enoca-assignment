package com.eukolos.companycase.service;

import com.eukolos.companycase.dto.*;
import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.repository.CompanyRepository;
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
public class CompanyServiceTest {
    @Mock
    private CompanyRepository repository;
    @InjectMocks
    private CompanyService service;


    @Test
    void shouldReturnCompanyDtoWhenHiredCompany() {
        //given
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );
        CompanyCreateRequest companyCreateRequest = new CompanyCreateRequest(
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54"
        );


        //when
        when(repository.save(any(Company.class))).thenReturn(company);

        CompanyDto response = service.saveCompany(companyCreateRequest);
        assertEquals("enoca", response.companyName());
        assertEquals("contact@enoca.com", response.email());
    }

    @Test
    void shouldReturnCompanyDtoListWhenFindAllCompany() {
        //given
        Company company1 = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        Company company2 = new Company(
                2L,
                "ipragaz",
                "Karabük",
                "contact@ipragaz.com",
                "+90 123 221 73 54",
                new ArrayList<>()
        );


        List<Company> companyList = new ArrayList<>();
        companyList.add(company1);
        companyList.add(company2);
        //when
        when(repository.findAll()).thenReturn(companyList);

        List<CompanyDto> response = service.getAllCompanies();
        assertEquals(2, response.size());
        assertEquals("contact@enoca.com", response.get(0).email());
    }

    @Test
    void shouldReturnCompanyDtoWhenFindById() {
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );


        Long id = 1L;

        //when
        when(repository.findById(id)).thenReturn(Optional.of(company));

        CompanyDto response = service.getCompanyById(id);
        assertEquals("enoca", response.companyName());
        assertEquals("contact@enoca.com", response.email());
    }

    @Test
    void shouldReturnCompanyDtoWhenFindByName() {
        Company company = new Company(
                1L,
                "enoca",
                "Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye",
                "contact@enoca.com",
                "+90 850 221 73 54",
                new ArrayList<>()
        );

        //when
        when(repository.findCompanyByCompanyName("enoca")).thenReturn(Optional.of(company));

        Company response = service.getCompanyByName("enoca");
        assertEquals("enoca", response.getCompanyName());
        assertEquals("contact@enoca.com", response.getEmail());
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

        CompanyCreateRequest updateRequest = new CompanyCreateRequest(
                "ipragaz güncel",
                "Karabük",
                "contact@ipragaz.com",
                "+90 123 221 73 54"
        );

        Company updatedCompany = new Company(
                2L,
                "ipragaz güncel",
                "Karabük",
                "contact@ipragaz.com",
                "+90 123 221 73 54",
                new ArrayList<>()
        );





        //when
        when(repository.findById(2L)).thenReturn(Optional.of(company));
        when(repository.save(any(Company.class))).thenReturn(updatedCompany);


        CompanyDto response = service.updateCompanyById(2L, updateRequest);
        assertEquals("ipragaz güncel", response.companyName());

    }
}
