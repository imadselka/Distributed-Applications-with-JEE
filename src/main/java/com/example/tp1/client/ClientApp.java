package com.example.tp1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.example.tp1.model.Address;

public class ClientApp {

    private static final String BASE_URL = "http://localhost:8080/tp1";
    private static final AddressBookClient client = new AddressBookClient(BASE_URL);
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            boolean exit = false;
            while (!exit) {
                printMenu();
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        addAddress();
                        break;
                    case "2":
                        deleteAddress();
                        break;
                    case "3":
                        searchByPerson();
                        break;
                    case "4":
                        searchByCity();
                        break;
                    case "5":
                        listAllAddresses();
                        break;
                    case "6":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Address Book Client =====");
        System.out.println("1. Add Address");
        System.out.println("2. Delete Address");
        System.out.println("3. Search by Person Name");
        System.out.println("4. Search by City");
        System.out.println("5. List All Addresses");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addAddress() throws IOException, InterruptedException {
        System.out.println("\n----- Add Address -----");
        System.out.print("Person Name: ");
        String personName = reader.readLine();

        System.out.print("Street Number: ");
        String streetNumber = reader.readLine();

        System.out.print("Street Name: ");
        String streetName = reader.readLine();

        System.out.print("City: ");
        String cityName = reader.readLine();

        Address address = new Address(personName, streetName, streetNumber, cityName);
        boolean success = client.addAddress(address);

        if (success) {
            System.out.println("Address added successfully!");
        } else {
            System.out.println("Failed to add address.");
        }
    }

    private static void deleteAddress() throws IOException, InterruptedException {
        System.out.println("\n----- Delete Address -----");
        System.out.print("Person Name: ");
        String personName = reader.readLine();

        System.out.print("Street Number: ");
        String streetNumber = reader.readLine();

        System.out.print("Street Name: ");
        String streetName = reader.readLine();

        System.out.print("City: ");
        String cityName = reader.readLine();

        Address address = new Address(personName, streetName, streetNumber, cityName);
        boolean success = client.deleteAddress(address);

        if (success) {
            System.out.println("Address deleted successfully!");
        } else {
            System.out.println("Failed to delete address.");
        }
    }

    private static void searchByPerson() throws IOException, InterruptedException {
        System.out.println("\n----- Search by Person Name -----");
        System.out.print("Enter person name: ");
        String personName = reader.readLine();

        List<Address> results = client.searchByPersonName(personName);
        displayResults(results);
    }

    private static void searchByCity() throws IOException, InterruptedException {
        System.out.println("\n----- Search by City -----");
        System.out.print("Enter city name: ");
        String cityName = reader.readLine();

        List<Address> results = client.searchByCity(cityName);
        displayResults(results);
    }

    private static void listAllAddresses() throws IOException, InterruptedException {
        System.out.println("\n----- All Addresses -----");
        List<Address> addresses = client.getAllAddresses();
        displayResults(addresses);
    }

    private static void displayResults(List<Address> addresses) {
        if (addresses.isEmpty()) {
            System.out.println("No addresses found.");
            return;
        }

        System.out.println("\nResults:");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-20s %-30s %-15s%n", "Person", "Address", "City");
        System.out.println("--------------------------------------------------");

        for (Address address : addresses) {
            System.out.printf("%-20s %-30s %-15s%n",
                    address.getPersonName(),
                    address.getStreetNumber() + " " + address.getStreetName(),
                    address.getCityName());
        }
        System.out.println("--------------------------------------------------");
    }
}
