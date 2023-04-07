package com.nighthawk.spring_portfolio.mvc.judge0;

import com.google.gson.Gson;

import org.apache.http.client.methods.HttpGet;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Base64;
import java.util.Optional;


@RestController
@RequestMapping("/j0/")
public class Judge0APIController {
    private static final String API_URL = "http://34.205.167.218:2358/";

    @PostMapping("/run")
    public String runCode(@RequestBody String code) throws IOException {
        String submissionId = submitCode(code);
        // return pollSubmissionStatus(submissionId);
        return submissionId;
    }

    private String submitCode(String code) throws IOException {

        String requestJson = "{\"stdin\":\"\",\"language_id\":62,\"source_code\":\""+code+"\"}";
        System.out.println("Request: dude " + requestJson);


        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL + "submissions/?base64_encoded=true"))
            .header("content-type", "application/json")
            .header("Content-Type", "application/json")
            .method("POST", HttpRequest.BodyPublishers.ofString("{\r\n    \"language_id\": 52,\r\n    \"source_code\": \"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\r\n    \"stdin\": \"SnVkZ2Uw\"\r\n}"))
            .build();
        
            try {
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                
                System.out.println(response.body());
                return (String) response.body();
            } catch (InterruptedException e) {
                // Handle the InterruptedException exception here
                return "Error";
            }

        // Map<String, Object> responseData = new Gson().fromJson(responseJson, HashMap.class);
        // System.out.println(responseData.get("token"));
    }

    /*
    private String pollSubmissionStatus(String submissionId) throws IOException {
        while (true) {
            HttpGet request = new HttpGet(API_URL + "submissions/" + submissionId + "?base64_encoded=true");
    
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            String responseJson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    
            Map<String, Object> responseData = new Gson().fromJson(responseJson, HashMap.class);
            System.out.println(responseData);
            System.out.println("Bruhhhhhh" + responseData.getClass());
            Map<String, Object> status = (Map<String, Object>) responseData.get("status");
            String description = (String) status.get("description");

            // String status = responseData.get("status").getAsJsonObject().get("description").getAsString();

    
            if ("In Queue".equals(description) || "Processing".equals(description)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                String stdout = (String) responseData.get("stdout");
                if (stdout != null && !stdout.isEmpty()) {
                    Optional<Base64.Decoder> decoder = Optional.ofNullable(Base64.getDecoder());
    
                    byte[] decodedBytes = decoder.map(d -> d.decode(stdout)).orElse(new byte[0]);
                    String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
                    return decodedString;
                } else {
                    return "";
                }
            }
        }
    }
    */

    public static void main(String[] args) {
        try {
            // java atob
            String str = "cHJpbnQgY2xhc3MgTWFpbiB7IHBvcnRzIHN0YXRpYyBvZiBjb25zdHJ1Y3QgZXh0ZXJpbmcgZXhwb3J0czt9IAoKRG9jdW1lbnQgY29uc3QgbWFpbigpIHsKCgkJc3lzdGVtLmZyb20oJ2MnKTsKfQo=";
            System.out.println(str);

            System.out.println(new Judge0APIController().submitCode(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
