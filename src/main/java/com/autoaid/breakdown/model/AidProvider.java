package com.autoaid.breakdown.model;

import jakarta.persistence.*;

@Entity
public class AidProvider {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_geolocation")
    private GeoLocation location;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }
}
