package com.ironhack.security.controllers.dtos;

import javax.validation.constraints.NotEmpty;

public class StudentNameDTO {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public StudentNameDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
