package com.nighthawk.spring_portfolio.mvc.judge0;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

@RestController
@RequestMapping("/j0/")
public class Judge0APIController {
    private static final String API_URL = "http://34.205.167.218:2358/";

    @PostMapping("/run")
    public String runCode(@RequestBody String code) throws IOException {
        String token = submitCode(code);
        return pollSubmissionStatus(token);
    }

    private String submitCode(String code) throws IOException {

        String requestJson = "{\"stdin\":\"\",\"language_id\":62,\"source_code\":\"" + code + "\"}";
        System.out.println("Request: dude " + requestJson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "submissions/?base64_encoded=true"))
                .header("content-type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestJson))
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            String token = jsonObject.get("token").getAsString();
            return token;
        } catch (InterruptedException e) {
            // Handle the InterruptedException exception here
            return "Error";
        }

        // Map<String, Object> responseData = new Gson().fromJson(responseJson,
        // HashMap.class);
        // System.out.println(responseData.get("token"));
    }


    private String pollSubmissionStatus(String token) throws IOException {
        // keep doing this request until the status is not "In Queue" or "Compilation Error"
        String responseJson = "";
        while (true) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            API_URL + "submissions/" + token + "?base64_encoded=true&fields=*"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            try {
                HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                        HttpResponse.BodyHandlers.ofString());
                responseJson = response.body();
            } catch (InterruptedException e) {
                // Handle the InterruptedException exception here
                return "Error";
            }

            JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
            String status = jsonObject.get("status").getAsJsonObject().get("description").getAsString();
            if (status.equals("Compilation Error")) {
                break;
            }
            if (status.equals("Accepted")) {
                break;
            }
        }
        // return the responseJson with the key of stdout
        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
        String stdout = jsonObject.get("stdout").getAsString();
        stdout = stdout.replaceAll("\n", "");
        stdout = stdout.replaceAll("=", "");
        stdout = new String(java.util.Base64.getDecoder().decode(stdout));
        return stdout;
    }
    public static void main(String[] args) {
        try {
            // java atob
            String str = "Y2xhc3MgTWFpbiB7CiBwdWJsaWMgc3RhdGljIHZvaWQgbWFpbihTdHJpbmdbXSBhcmdzKSB7CiAgU3lzdGVtLm91dC5wcmludGxuKCJIZWxsbyBXb3JsZCEiKTsKfQp9";
            System.out.println(str);

            System.out.println(new Judge0APIController().runCode(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
