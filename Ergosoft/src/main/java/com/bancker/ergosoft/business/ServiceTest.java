package com.bancker.ergosoft.business;

import com.bancker.ergosoft.persistence.entity.Patient;
import com.bancker.ergosoft.persistence.repository.PatientRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Service
public class ServiceTest {

    static Logger log = Logger.getLogger(ServiceTest.class);

    @Autowired
    private PatientRepository patientRepository;

    /*@PostConstruct
    private void init(){

        Patient patient=new Patient();
        patient.setId(null);
        patient.setName("Nikolic");
        patient.setFirstName("Charlene");
        patient.setEmail("charlene.nikolic@gmail.com");
        Date birth=new Date(10000000l);
        patient.setFollowUp("Folie!");
        patient.setBirthDate(birth);
        patientRepository.save(patient);
        log.info("----------------IT WORKED !----------------------");

        Iterable<Patient> patientList=patientRepository.findAll();
        patient=patientList.iterator().next();
        log.info("-------------------"+patient.getId()+ patient.getName()+"------------------");
    }*/
}
