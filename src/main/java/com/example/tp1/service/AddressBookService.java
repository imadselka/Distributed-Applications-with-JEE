package com.example.tp1.service;

import com.example.tp1.model.Address;
import com.example.tp1.model.AddressBook;

import java.util.List;

public class AddressBookService {
    private static AddressBook addressBook = new AddressBook("Main Address Book");

    private static final AddressBookService INSTANCE = new AddressBookService();

    private AddressBookService() {
    }

    public static AddressBookService getInstance() {
        return INSTANCE;
    }

    /**
     * Add a new address to the address book
     */
    public void addAddress(Address address) throws ServiceException {
        try {
            addressBook.addAddress(address);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Failed to add address: " + e.getMessage(), e);
        }
    }

    /**
     * Remove an address from the address book
     */
    public boolean removeAddress(Address address) throws ServiceException {
        try {
            return addressBook.removeAddress(address);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Failed to remove address: " + e.getMessage(), e);
        }
    }

    /**
     * Search for addresses by person name
     */
    public List<Address> findAddressByPersonName(String personName) throws ServiceException {
        try {
            return addressBook.findAddressByPersonName(personName);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Failed to search addresses: " + e.getMessage(), e);
        }
    }

    /**
     * Search for addresses by city
     */
    public List<Address> findAddressByCity(String cityName) throws ServiceException {
        try {
            return addressBook.findAddressByCity(cityName);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Failed to search addresses: " + e.getMessage(), e);
        }
    }

    /**
     * Get all addresses
     */
    public List<Address> getAllAddresses() {
        return addressBook.getAddresses();
    }

    /**
     * Get the address book name
     */
    public String getAddressBookName() {
        return addressBook.getName();
    }

    /**
     * Set the address book name
     */
    public void setAddressBookName(String name) {
        addressBook.setName(name);
    }
}

