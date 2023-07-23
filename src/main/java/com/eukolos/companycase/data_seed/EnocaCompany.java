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
public class EnocaCompany implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    // generated on https://mockaroo.com/

    @Override
    public void run(String... args) {
        Company enoca = companyRepository.save(
                Company.builder()
                        .companyName("enoca")
                        .phoneNumber("+90 850 221 73 54")
                        .email("contact@enoca.com")
                        .address("Flora Residans Vedat Günyol Caddesi Defne Sokak No:1 Kat:25 Ofis:2501,2502, 34750 Küçükbakkalköy, Ataşehir, İstanbul Türkiye")
                        .build()
        );

        Employee enocaEmployee1 = Employee.builder()
                .firstName("Giffard")
                .lastName("Byneth")
                .email("gbyneth0@yellowbook.com")
                .phoneNumber("783-679-2147")
                .salary(16000.00)
                .department(Department.RESEARCH)
                .company(enoca)
                .build();
        Employee enocaEmployee2 = Employee.builder()
                .firstName("Janna")
                .lastName("Plitz")
                .email("jplitz1@whitehouse.gov")
                .phoneNumber("936-560-8405")
                .salary(23000.00)
                .department(Department.SUPPORT)
                .company(enoca)
                .build();
        Employee enocaEmployee3 = Employee.builder()
                .firstName("Red")
                .lastName("Froude")
                .email("rfroude2@princeton.edu")
                .phoneNumber("758-257-4750")
                .salary(19000.00)
                .department(Department.SERVICES)
                .company(enoca)
                .build();
        Employee enocaEmployee4 = Employee.builder()
                .firstName("Jermaine")
                .lastName("Cotsford")
                .email("jcotsford3@themeforest.net")
                .phoneNumber("324-677-0234")
                .salary(20000.00)
                .department(Department.HUMAN_RESOURCE)
                .company(enoca)
                .build();
        Employee enocaEmployee5 = Employee.builder()
                .firstName("Vi")
                .lastName("Banghe")
                .email("vbanghe4@cdc.gov")
                .phoneNumber("839-886-0126")
                .salary(18000.00)
                .department(Department.SALES)
                .company(enoca)
                .build();
        Employee enocaEmployee6 = Employee.builder()
                .firstName("Harwilll")
                .lastName("Perrinchief")
                .email("hperrinchiefk@si.edu")
                .phoneNumber("954-511-2677")
                .salary(18000.00)
                .department(Department.SUPPORT)
                .company(enoca)
                .build();
        Employee enocaEmployee7 = Employee.builder()
                .firstName("Nathaniel")
                .lastName("Rathjen")
                .email("nrathjenl@wikispaces.com")
                .phoneNumber("507-965-1046")
                .salary(18000.00)
                .department(Department.RESEARCH)
                .company(enoca)
                .build();
        employeeRepository.saveAll(List.of(
                enocaEmployee1,
                enocaEmployee2,
                enocaEmployee3,
                enocaEmployee4,
                enocaEmployee5,
                enocaEmployee6,
                enocaEmployee7
        ));
    }
}
