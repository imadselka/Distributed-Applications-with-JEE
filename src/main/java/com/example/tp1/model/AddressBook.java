package com.example.tp1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.tp1.model.Address;

public class AddressBook implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Address> addresses;

    public AddressBook() {
        this.addresses = new ArrayList<>();
    }

    public AddressBook(String name) {
        this.name = name;
        this.addresses = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    // Business methods
    public void addAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        addresses.add(address);
    }

    public boolean removeAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        return addresses.remove(address);
    }

    public List<Address> findAddressByPersonName(String personName) {
        if (personName == null || personName.trim().isEmpty()) {
            throw new IllegalArgumentException("Person name cannot be null or empty");
        }

        return addresses.stream()
                .filter(a -> a.getPersonName().toLowerCase().contains(personName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Address> findAddressByCity(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }

        return addresses.stream()
                .filter(a -> a.getCityName().toLowerCase().contains(cityName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return addresses.size();
    }
}