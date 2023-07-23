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
public class GratisCompany implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    // generated on https://mockaroo.com/

    @Override
    public void run(String... args) throws Exception {
        Company gratis = companyRepository.save(
                Company.builder()
                        .companyName("gratis")
                        .phoneNumber("+90 212 313 10 00")
                        .email("info@gratis.com")
                        .address("gratis Küçükyalı Plaza, Aydınevler Mahallesi İnönü Caddesi No:20 Küçükyalı Ofispark B Blok - Maltepe / İSTANBUL")
                        .build()
        );

        Employee gratisEmployee1 = Employee.builder()
                .firstName("Mayer")
                .lastName("McInnerny")
                .email("mmcinnernyv@lulu.com")
                .phoneNumber("897-314-9418")
                .salary(16000.00)
                .department(Department.SALES)
                .company(gratis)
                .build();
        Employee gratisEmployee2 = Employee.builder()
                .firstName("Merl")
                .lastName("Llewellin")
                .email("mllewellinw@tumblr.com")
                .phoneNumber("702-479-8380")
                .salary(20000.00)
                .department(Department.SALES)
                .company(gratis)
                .build();
        Employee gratisEmployee3 = Employee.builder()
                .firstName("Harrie")
                .lastName("Forst")
                .email("hforstx@nba.com")
                .phoneNumber("593-127-5844")
                .salary(18000.00)
                .department(Department.SUPPORT)
                .company(gratis)
                .build();
        Employee gratisEmployee4 = Employee.builder()
                .firstName("Doe")
                .lastName("Vaulkhard")
                .email("dvaulkhardy@goo.ne.jp")
                .phoneNumber("538-466-75866")
                .salary(23000.00)
                .department(Department.ACCOUNTING)
                .company(gratis)
                .build();
        Employee gratisEmployee5 = Employee.builder()
                .firstName("Leonerd")
                .lastName("Dowling")
                .email("ldowlingz@zimbio.com")
                .phoneNumber("670-712-5316")
                .salary(25000.00)
                .department(Department.RESEARCH)
                .company(gratis)
                .build();

        employeeRepository.saveAll(List.of(
                gratisEmployee1,
                gratisEmployee2,
                gratisEmployee3,
                gratisEmployee4,
                gratisEmployee5
        ));
    }
}
