package com.example.tp1.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tp1.model.Address;

public class AddressBookClient {

    private final String baseUrl;
    private final HttpClient httpClient;

    public AddressBookClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    /**
     * Add a new address to the address book
     */
    public boolean addAddress(Address address) throws IOException, InterruptedException {
        Map<String, String> formData = new HashMap<>();
        formData.put("personName", address.getPersonName());
        formData.put("streetName", address.getStreetName());
        formData.put("streetNumber", address.getStreetNumber());
        formData.put("cityName", address.getCityName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/address/"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormDataFromMap(formData))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200 || response.statusCode() == 302;
    }

    /**
     * Delete an address from the address book
     */
    public boolean deleteAddress(Address address) throws IOException, InterruptedException {
        Map<String, String> formData = new HashMap<>();
        formData.put("personName", address.getPersonName());
        formData.put("streetName", address.getStreetName());
        formData.put("streetNumber", address.getStreetNumber());
        formData.put("cityName", address.getCityName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/address/delete"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(buildFormDataFromMap(formData))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() == 200 || response.statusCode() == 302;
    }

    /**
     * Search for addresses by person name
     */
    public List<Address> searchByPersonName(String personName) throws IOException, InterruptedException {
        String encodedName = URLEncoder.encode(personName, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/address/search?searchType=person&searchTerm=" + encodedName))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // In a real implementation, we would parse the HTML response or use a JSON API
        // For this example, we'll return an empty list
        return new ArrayList<>();
    }

    /**
     * Search for addresses by city
     */
    public List<Address> searchByCity(String cityName) throws IOException, InterruptedException {
        String encodedCity = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/address/search?searchType=city&searchTerm=" + encodedCity))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // In a real implementation, we would parse the HTML response or use a JSON API
        // For this example, we'll return an empty list
        return new ArrayList<>();
    }

    /**
     * Get all addresses
     */
    public List<Address> getAllAddresses() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/address/"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // In a real implementation, we would parse the HTML response or use a JSON API
        // For this example, we'll return an empty list
        return new ArrayList<>();
    }

    private HttpRequest.BodyPublisher buildFormDataFromMap(Map<String, String> formData) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
