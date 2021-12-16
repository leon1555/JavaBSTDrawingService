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
    public static void postInput(int[] inputArray) throws JsonProcessingException {
        String input = Arrays.toString(inputArray);
//        String input = inputWithCommasAndBrackets.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
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
                System.out.println("New record created!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}