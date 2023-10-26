package com.nighthawk.hacks.classDataStruct;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherRequest {
    public static void main(String[] args) {
        // Specify the latitude and longitude
        double latitude = 33.01479454987898;
        double longitude = -117.12140255005595;

        // Create the URI with the latitude and longitude
        URI uri = URI.create("https://api.weather.gov/points/" + latitude + "," + longitude);

        // Create the HTTP request with the API key
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-RapidAPI-Key", "cb84e1853amsh36bd127c21f5c41p12163cjsn5ce359cf2f1a")
                .header("User-Agent", "Java HttpClient")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            // Send the request
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}