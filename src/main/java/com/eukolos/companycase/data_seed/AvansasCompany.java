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
public class AvansasCompany implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    // generated on https://mockaroo.com/

    @Override
    public void run(String... args) {
        Company avansas = companyRepository.save(
                Company.builder()
                        .companyName("Avansas")
                        .phoneNumber("+90 216 365 78 00")
                        .email("destek@avansas.com")
                        .address("Avansas Ofis Malzemeleri Tic. A.Ş. Kısıklı Mah. Ferah Cad. No:10 Kat: 4-5-6-7 Üsküdar 34692 İstanbul / Türkiye")
                        .build()
        );

        Employee avansasEmployee1 = Employee.builder()
                .firstName("Laurena")
                .lastName("McKomb")
                .email("lmckomba@purevolume.com")
                .phoneNumber("129-682-0848")
                .salary(16000.00)
                .department(Department.SERVICES)
                .company(avansas)
                .build();
        Employee avansasEmployee2 = Employee.builder()
                .firstName("Lynnett")
                .lastName("Potteridge")
                .email("lpotteridgeb@ezinearticles.com")
                .phoneNumber("907-967-0311")
                .salary(20000.00)
                .department(Department.SALES)
                .company(avansas)
                .build();
        Employee avansasEmployee3 = Employee.builder()
                .firstName("Inga")
                .lastName("Puddefoot")
                .email("ipuddefoote@wordpress.org")
                .phoneNumber("366-845-9812")
                .salary(21000.00)
                .department(Department.ACCOUNTING)
                .company(avansas)
                .build();
        Employee avansasEmployee4 = Employee.builder()
                .firstName("Enoch")
                .lastName("Halsey")
                .email("ehalseyf@so-net.ne.jp")
                .phoneNumber("497-972-9016")
                .salary(25000.00)
                .department(Department.ACCOUNTING)
                .company(avansas)
                .build();
        Employee avansasEmployee5 = Employee.builder()
                .firstName("Norean")
                .lastName("Bramer")
                .email("nbramerg@surveymonkey.com")
                .phoneNumber("873-670-9484")
                .salary(17000.00)
                .department(Department.HUMAN_RESOURCE)
                .company(avansas)
                .build();
        Employee avansasEmployee6 = Employee.builder()
                .firstName("Arvie")
                .lastName("Gianolini")
                .email("agianolinih@yahoo.co.jp")
                .phoneNumber("846-942-1815")
                .salary(23000.00)
                .department(Department.HUMAN_RESOURCE)
                .company(avansas)
                .build();
        Employee avansasEmployee7 = Employee.builder()
                .firstName("Susan")
                .lastName("Corker")
                .email("scorkerj@cornell.edu")
                .phoneNumber("618-863-6296")
                .salary(19000.00)
                .department(Department.HUMAN_RESOURCE)
                .company(avansas)
                .build();

        employeeRepository.saveAll(List.of(
                avansasEmployee1,
                avansasEmployee2,
                avansasEmployee3,
                avansasEmployee4,
                avansasEmployee5,
                avansasEmployee6,
                avansasEmployee7
        ));

    }

}
