package com.bancker.ergosoft.persistence.entity;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Session {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String type;

    @Column(nullable = false)
    private int price;

    private boolean paid;

    private Long payDate;
}
