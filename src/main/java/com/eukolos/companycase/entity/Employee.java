package com.eukolos.companycase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
// @Setter breaks immutability
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private double salary;
    @CreationTimestamp
    private LocalDate hireDate;
    @Enumerated(EnumType.STRING)
    private Department department;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
