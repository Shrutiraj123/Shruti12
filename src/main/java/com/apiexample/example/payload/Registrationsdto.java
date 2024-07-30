package com.apiexample.example.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Getter
@Setter

public class Registrationsdto {

    private Long id;

    @Size(min=2, message = "Should be more than 2 charcters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;


    private String mobile;

    private  String message;

}