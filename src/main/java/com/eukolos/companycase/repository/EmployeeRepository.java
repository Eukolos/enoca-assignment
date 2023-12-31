package com.eukolos.companycase.repository;

import com.eukolos.companycase.entity.Department;
import com.eukolos.companycase.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findEmployeeByCompany_Id(Long id);
    Optional<Employee> findEmployeeByEmail(String name);

    Optional<List<Employee>> findEmployeeByCompanyCompanyNameAndDepartment(String companyName, Department department);

}
