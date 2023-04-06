package com.nighthawk.spring_portfolio.mvc.judge0;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
        return pollSubmissionStatus(submissionId);
    }

    private String submitCode(String code) throws IOException {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("source_code", code);
        requestData.put("language_id", 62); // Java language ID
        requestData.put("stdin", "");

        String requestJson = new Gson().toJson(requestData);

        HttpPost request = new HttpPost(API_URL + "submissions");
        request.setHeader("Content-type", "application/json");
        request.setEntity(new StringEntity(requestJson, StandardCharsets.UTF_8));

        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseJson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

        Map<String, Object> responseData = new Gson().fromJson(responseJson, HashMap.class);
        return (String) responseData.get("token");
    }

    private String pollSubmissionStatus(String submissionId) throws IOException {
        while (true) {
            HttpGet request = new HttpGet(API_URL + "submissions/" + submissionId + "?base64_encoded=true");
    
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            String responseJson = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    
            Map<String, Object> responseData = new Gson().fromJson(responseJson, HashMap.class);
            String status = responseData.get("status").toString();
    
            if ("queued".equals(status) || "processing".equals(status)) {
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

    public static void main(String[] args) {
        try {
            // java atob
            String str = "public class Main { public static void main(String[] args) { System.out.println(\"Hello World\"); } }";
            String encoded = Base64.getEncoder().encodeToString(str.getBytes());

            System.out.println(encoded);

            System.out.println(new Judge0APIController().runCode(encoded));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
