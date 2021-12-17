package com.tree;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient {

    // Takes in the array of integers inputted by the user and makes an HTTP POST request to the Spring Boot API.
    public static void postInput(int[] inputArray) throws JsonProcessingException {
        String input = Arrays.toString(inputArray);
        Map<Object, Object> tree = new HashMap<>();
        tree.put("input", input);

        ObjectMapper posted = new ObjectMapper();
        String requestBody = posted.writeValueAsString(tree);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/tree"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 201) {
                System.out.println("User input has been logged to database.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
