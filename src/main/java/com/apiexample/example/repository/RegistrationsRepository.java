package com.apiexample.example.repository;

import com.apiexample.example.entities.Registrations;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface RegistrationsRepository extends JpaRepository<Registrations, Long> {

}