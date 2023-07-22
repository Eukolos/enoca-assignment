package com.eukolos.companycase.repository;

import com.eukolos.companycase.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository not needed because inherited
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
