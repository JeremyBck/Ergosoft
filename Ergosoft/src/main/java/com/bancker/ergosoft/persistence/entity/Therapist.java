package com.bancker.ergosoft.persistence.entity;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Therapist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String firstName;

    private String email;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private String city;

    @OneToMany
    private List<Patient> patientList;

}
