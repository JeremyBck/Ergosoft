package com.bancker.ergosoft.controller;

import com.bancker.ergosoft.persistence.entity.Document;
import com.bancker.ergosoft.persistence.entity.Patient;
import com.bancker.ergosoft.persistence.service.DocumentService;
import com.bancker.ergosoft.persistence.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/api/v0/document")
public class DocumentController {

    static Logger log = Logger.getLogger(DocumentController.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PatientService patientService;

    @Value("${fileManagement.rootPath}")
    private String ROOT;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getDocument(String patientEmail, String type, String name){
        Patient patient=patientService.findByEmail(patientEmail);
        if(patient==null){
            log.error("Patient unknown !");
            return new ResponseEntity<>("Patient not found !", HttpStatus.BAD_REQUEST);
        }
        String path=patient.getFirstName().concat("_"+patient.getName()+"/"+type+"/"+name);

    }

    /*public ResponseEntity getDocumentsName{

    }*/

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addDocument(@RequestParam("name") String name,
                                      @RequestParam("type") String type,
                                      @RequestParam("file") MultipartFile file,
                                      @RequestParam("patientEmail") String patientEmail) {
        if (name.contains("/") || name.contains(" ")) {
            return new ResponseEntity("Folder seperators and blanks spaces not allowed !", HttpStatus.BAD_REQUEST);
        }
        if (!file.isEmpty()) {
            Patient patient = patientService.findByEmail(patientEmail);
            if (patient == null) {
                return new ResponseEntity("Error: no patient found for this email !", HttpStatus.BAD_REQUEST);
            }
            String path = ROOT.concat("/" + patient.getFirstName() + "_" + patient.getName());
            new File(path).mkdir();
            path = path.concat("/" + type);
            new File(path).mkdir();
            try {
                storeFile(path, name, file);
                log.info("New document saved !");
                Document document=documentService.addDocument(new Document(null, type, name, patient));
                return new ResponseEntity(document, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity("You failed to upload " + name + " because the file was empty", HttpStatus.BAD_REQUEST);
        }
    }

    private void storeFile(String path, String fileName, MultipartFile file) throws Exception {
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(path + "/" + fileName)));
        FileCopyUtils.copy(file.getInputStream(), stream);
        stream.close();
    }

}
