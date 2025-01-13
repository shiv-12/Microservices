package com.project.rest.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Filter;

import java.time.LocalDate;


@JsonFilter("customFilter")
public class User {

    private int id;

    @Size(min = 2, message = "Name should contains at least 2 characters")
    private final String name;

    @Past(message = "Only past dates can be entered")
    private final LocalDate dob;

    public User(int id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDob() {
        return dob;
    }
}
