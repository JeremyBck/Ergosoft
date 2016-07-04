package com.bancker.ergosoft.persistence.entity;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Date;

@Data
@Entity
@Transactional
public class Timeslot {

    @Id
    @GeneratedValue
    private Long id;

    private long start;

    private long end;

    @OneToOne
    private Patient patient;

    @OneToOne
    private User user;

}
