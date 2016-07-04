package com.bancker.ergosoft.persistence.service;

import com.bancker.ergosoft.persistence.entity.Patient;
import com.bancker.ergosoft.persistence.entity.User;
import com.bancker.ergosoft.persistence.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    static Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientService patientService;

    public User addUser(User userToCreate){
        log.info("New user saved !");
        return userRepository.save(userToCreate);
    }

    public void removeUser(User userToDelete){
        List<Patient> patientList=patientService.findByUser(userToDelete);
        patientList.stream().forEach(patient -> {
            List<User> userList=patient.getUserList();
            if(!userList.remove(userToDelete)){
                log.warn("No patient was associated to this user !");
            }
            else{
                patient.setUserList(userList);
            }
        }
        );
        userRepository.delete(userToDelete);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
