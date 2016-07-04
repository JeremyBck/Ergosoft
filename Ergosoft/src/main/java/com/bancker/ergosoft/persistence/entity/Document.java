package com.bancker.ergosoft.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Patient patient;
}
