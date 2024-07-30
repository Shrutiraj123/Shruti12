package com.apiexample.example.service;

import com.apiexample.example.payload.Registrationsdto;

import java.util.List;

public interface RegistrationService {
    Registrationsdto createRegistration(Registrationsdto registrationdto);



    Registrationsdto updateRegistration(long id, Registrationsdto registrationsdto);

    default List<Registrationsdto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }


    Registrationsdto getRegistrationById(long id);

    void deleteRegistrationById(Long id);
}
