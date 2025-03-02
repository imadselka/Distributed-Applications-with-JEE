// src/main/java/com/addressbook/model/Address.java
package com.example.tp1.model;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String personName;
    private String streetName;
    private String streetNumber;
    private String cityName;

    // Default constructor required for serialization
    public Address() {
    }

    public Address(String personName, String streetName, String streetNumber, String cityName) {
        this.personName = personName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.cityName = cityName;
    }

    // Getters and Setters
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(personName, address.personName) &&
                Objects.equals(streetName, address.streetName) &&
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(cityName, address.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, streetName, streetNumber, cityName);
    }

    @Override
    public String toString() {
        return String.format("%s, %s %s, %s", personName, streetNumber, streetName, cityName);
    }
}

