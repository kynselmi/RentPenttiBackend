package com.kingofnone.rentpentti.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class Authorization {

    @Value("${nordea.api.endpoint}")
    private String apiUri;
    @Value("${nordea.api.token}")
    private String apiToken;
    @Value("${nordea.api.xIbmClientId}")
    private String xIbmClientId;
    @Value("${nordea.api.xIbmClientSecret}")
    private String xIbmClientSecret;
    private final static String ENDPOINT_SUFFIX = "v1/authentication/authorization";
    private final static String CLIENT_ID = "";
    private final static String CLIENT_SECRET = "";
    private final static String REDIRECT_URI = "https://www.httpbin.org/anything";

    // Headers
    private final static String X_IBM_CLIENT_ID = "X-IBM-Client-Id";
    private final static String X_IBM_CLIENT_SECRET = "X-IBM-Client-Secret";


    public String getToken() {
        ObjectNode objectNode = getAuthenticationJson();

        RestTemplate restTemplate = new RestTemplate();

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiToken);
        headers.add(X_IBM_CLIENT_ID, xIbmClientId);
        headers.add(X_IBM_CLIENT_SECRET, xIbmClientSecret);

        HttpEntity<String> request = new HttpEntity<>(objectNode.toString(), headers);
        String response = restTemplate.postForObject(apiUri+ENDPOINT_SUFFIX, request, String.class);
        System.out.println(response);
        return "";
    }

    private ObjectNode getAuthenticationJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("client_id", CLIENT_ID);
        objectNode.put("redirect_uri", REDIRECT_URI);
        objectNode.put("state", "");
        return objectNode;
    }
}
