package com.eukolos.companycase.data_seed;

import com.eukolos.companycase.entity.Company;
import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import com.eukolos.companycase.repository.CompanyRepository;
import com.eukolos.companycase.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TurkcellCompany implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    // generated on https://mockaroo.com/

    @Override
    public void run(String... args) throws Exception {
        Company turkcell = companyRepository.save(
                Company.builder()
                        .companyName("Turkcell")
                        .phoneNumber("+90 212 313 10 00")
                        .email("info@turkcell.com")
                        .address("Turkcell Küçükyalı Plaza, Aydınevler Mahallesi İnönü Caddesi No:20 Küçükyalı Ofispark B Blok - Maltepe / İSTANBUL")
                        .build()
        );

        Employee turkcellEmployee1 = Employee.builder()
                .firstName("Albina")
                .lastName("Yegorov")
                .email("ayegorovm@netvibes.com")
                .phoneNumber("711-549-4959")
                .salary(16000.00)
                .department(Department.SALES)
                .company(turkcell)
                .build();
        Employee turkcellEmployee2 = Employee.builder()
                .firstName("Codie")
                .lastName("Eason")
                .email("ceasonn@odnoklassniki.ru")
                .phoneNumber("420-304-7116")
                .salary(20000.00)
                .department(Department.RESEARCH)
                .company(turkcell)
                .build();
        Employee turkcellEmployee3 = Employee.builder()
                .firstName("Bernetta")
                .lastName("Dash")
                .email("bdasho@xinhuanet.com")
                .phoneNumber("714-180-0949")
                .salary(18000.00)
                .department(Department.SUPPORT)
                .company(turkcell)
                .build();
        Employee turkcellEmployee4 = Employee.builder()
                .firstName("Carmina")
                .lastName("Duding")
                .email("cdudingp@nba.com")
                .phoneNumber("266-688-8576")
                .salary(23000.00)
                .department(Department.ACCOUNTING)
                .company(turkcell)
                .build();
        Employee turkcellEmployee5 = Employee.builder()
                .firstName("Erda")
                .lastName("Reide")
                .email("ereideq@ucla.edu")
                .phoneNumber("521-463-8292")
                .salary(25000.00)
                .department(Department.RESEARCH)
                .company(turkcell)
                .build();

        employeeRepository.saveAll(List.of(
                turkcellEmployee1,
                turkcellEmployee2,
                turkcellEmployee3,
                turkcellEmployee4,
                turkcellEmployee5
        ));
    }
}
