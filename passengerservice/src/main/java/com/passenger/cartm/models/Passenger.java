package com.passenger.cartm.models;

public class Passenger {
    private Long id;
    private String name;    
    private String email_id;

    public Passenger() {}

    public Passenger(Long id, String name, String email_id) {
        this.id = id;
        this.name = name;
        this.email_id = email_id;
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
        return email_id;
    }
    public void setEmailId(String email_id) {
        this.email_id = email_id;
    }
}
