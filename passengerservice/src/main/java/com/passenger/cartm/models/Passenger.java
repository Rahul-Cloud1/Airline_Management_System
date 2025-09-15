
package com.passenger.cartm.models;

import java.time.LocalDate;

public class Passenger {
    private Long id;
    private String name;
    private String emailId;
    private String phone;
    private String passportNumber;
    private LocalDate dateOfBirth;

    public Passenger() {}

    public Passenger(Long id, String name, String emailId, String phone, String passportNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.phone = phone;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
