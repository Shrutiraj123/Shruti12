package com.apiexample.example.controller;

import com.apiexample.example.entities.Registrations;
import com.apiexample.example.payload.Registrationsdto;
import com.apiexample.example.service.RegistrationService;
import jakarta.persistence.Id;
import jakarta.servlet.Registration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;



    @PostMapping
    public ResponseEntity<?> addRegistration(@Valid @RequestBody Registrationsdto registrationdto, BindingResult result) {
        if (result.hasErrors()){
            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        Registrationsdto registration = registrationService.createRegistration(registrationdto);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam Long id) {
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("Registration deleted", HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> updateRegistration(
            @RequestParam long id,
            @Valid @RequestBody Registrationsdto registrationsdto,
            BindingResult  result
    ) {
         if (result.hasErrors()){
             return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
         }

        Registrationsdto registrationsdto1 = registrationService.updateRegistration(id, registrationsdto);

        return new ResponseEntity<>(registrationsdto1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Registrationsdto>> getAllRegistration(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(name="sortBy", defaultValue = "name",required = false)String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "name",required = false)String sortDir
            ){

        List<Registrationsdto> registration = registrationService.getAllRegistration(pageNo, pageSize, sortBy,sortDir);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }
    @GetMapping("/byid")
    public ResponseEntity<Registrationsdto>getRegistrationById(
            @RequestParam long id
    ){
        Registrationsdto registrationsdto= registrationService.getRegistrationById(id);
        return  new ResponseEntity<>(registrationsdto,HttpStatus.OK);

    }
}