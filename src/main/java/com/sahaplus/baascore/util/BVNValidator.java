package com.sahaplus.baascore.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sahaplus.baascore.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@Component
@RequestMapping("/api")
public class BVNValidator {

    public RestTemplate restTemplate = new RestTemplate();

    @Value("${nibss.clientId}")
    private String clientId;

    @Value("${nibss.clientSecret}")
    private String clientSecret;

    @Value("${nibss.saha.userId}")
    private String userId;

    @Value("${nibss.chanelCode}")
    private String chanelCode;

    @Value("${nibss.bvnDetailsUrl}")
    private String nibssBvnDetailsUrl;

    @Value("${nibss.scopes}")
    private String scopes;

    @Value("${nibss.redirectUrl}")
    private String redirectUrl;

    @Value("${nibss.issuer}")
    private String issuer;

    @Value("${nibss.authUrl}")
    private String authUrl;

    @Value("${nibss.tokenUrl}")
    private String tokenUrl;

    @GetMapping("/authorize")
    public ResponseEntity<String> authorize() {
        String authorizationUrl = buildAuthorizationUrl();
        return ResponseEntity.ok(authorizationUrl);
    }

    @PostMapping("/exchange-code")
    public ResponseEntity<String> exchangeCode(@RequestParam String code) {
        String accessToken = exchangeCodeForAccessToken(code);
        return ResponseEntity.ok("Access Token: " + accessToken);
    }

    private String buildAuthorizationUrl() {
        return authUrl +
                "?scope=" + scopes +
                "&response_type=code" +
                "&redirect_uri=" + redirectUrl +
                "&client_id=" + clientId;
    }

    private String exchangeCodeForAccessToken(String code) {
        String tokenEndpoint = tokenUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("code", code);
        requestBody.add("redirect_uri", redirectUrl);
        requestBody.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, requestEntity, String.class);

        return extractAccessTokenFromResponse(response.getBody());
    }


    private String extractAccessTokenFromResponse(String tokenResponse) {
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(tokenResponse, JsonObject.class);

        if (jsonResponse.has("access_token")) {
            log.info("Access Token: {}", jsonResponse.get("access_token").getAsString());
            return jsonResponse.get("access_token").getAsString();
        } else {
            throw new ApiException("Access token not found in response");
        }
    }

    public BVNDetails getBvnDetails (String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-consumer-unique-id",chanelCode+userId);
        headers.set("x-consumer-custom-id", clientId);
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<BVNDetails> response = restTemplate.postForEntity(nibssBvnDetailsUrl, entity, BVNDetails.class);
        log.info("Response: {}", response);

        return response.getBody();
    }

    public boolean validateBvn(String token, String nin) {
        log.info("Verifying BVN: {}", nin);
        BVNDetails bvnDetails = getBvnDetails(token);
        log.info("BVN Details: {}", bvnDetails);
        return bvnDetails.getNin().equals(nin);
    }
}

