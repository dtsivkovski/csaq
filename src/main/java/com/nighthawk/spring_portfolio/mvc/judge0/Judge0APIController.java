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
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
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
                return new String(java.util.Base64.getDecoder().decode((String) responseData.get("stdout")),
                        StandardCharsets.UTF_8);
            }
        }
    }

}
