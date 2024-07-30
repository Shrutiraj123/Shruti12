package com.apiexample.example.service;

import com.apiexample.example.entities.Registrations;
import com.apiexample.example.exception.ResourceNotFound;
import com.apiexample.example.payload.Registrationsdto;
import com.apiexample.example.repository.RegistrationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationsRepository registrationsRepository;

    @Override
    public Registrationsdto createRegistration(Registrationsdto registrationdto) {
        Registrations registrations = mapToEntity(registrationdto);
        Registrations saveEntity = registrationsRepository.save(registrations);
        Registrationsdto dto = mapTodto(saveEntity);
        dto.setMessage("Registrations saved");
        return dto;
    }



    @Override
    public Registrationsdto updateRegistration(long id, Registrationsdto registrationsdto) {
        Optional<Registrations> optional = registrationsRepository.findById(id);
        Registrations registrations= optional.get();

        registrations.setName(registrationsdto.getName());
        registrations.setEmail(registrationsdto.getEmail());
        registrations.setMobile(registrationsdto.getMobile());
        Registrations save = registrationsRepository.save(registrations);
        Registrationsdto registrationsdto1 = mapTodto(registrations);
        return registrationsdto1;
    }

    @Override
    public List<Registrationsdto> getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {
        List<Registrations> registrations = registrationsRepository.findAll();
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        registrationsRepository.findAll(pageRequest);
        List<Registrationsdto> registrationsdtos = registrations.stream().map(r -> mapTodto(r)).collect(Collectors.toList());
        return registrationsdtos;
    }

    @Override
    public Registrationsdto getRegistrationById(long id) {
        Registrations registrations1 = registrationsRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Registration not found with id:" + id));
        Registrationsdto registrationsdto = mapTodto(registrations1);
        return registrationsdto;
    }

    @Override
    public void deleteRegistrationById(Long id) {
    }



    Registrations mapToEntity(Registrationsdto dto) {
        Registrations entity = new Registrations();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        return entity;

    }

    Registrationsdto mapTodto(Registrations registrations) {
        Registrationsdto dto = new Registrationsdto();
        dto.setId(registrations.getId());
        dto.setName(registrations.getName());
        dto.setEmail(registrations.getEmail());
        dto.setMobile(registrations.getMobile());
        return dto;

    }
}