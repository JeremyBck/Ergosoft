package com.bancker.ergosoft.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class Timeslot {

    @Id
    @GeneratedValue
    private Long id;

    //TODO:convert into sql date ?
    private long start;

    private long end;

    private long price;

    private boolean paid;

    @OneToOne
    private Patient patient;

    @OneToOne
    private User user;

}
