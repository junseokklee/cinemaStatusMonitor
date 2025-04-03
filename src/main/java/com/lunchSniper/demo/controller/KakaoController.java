package com.lunchSniper.demo.controller;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class KakaoController {

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody String message, Principal principal) {
        String accessToken = getAccessToken(principal);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject body = new JSONObject();
        body.put("object_type", "text");
        body.put("text", message);
        body.put("link", new JSONObject().put("web_url", "http://your-website.com"));

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        return response;
    }

    private String getAccessToken(Principal principal) {
        // accessToken을 가져오는 로직 구현
        return "YOUR_ACCESS_TOKEN";
    }
}

