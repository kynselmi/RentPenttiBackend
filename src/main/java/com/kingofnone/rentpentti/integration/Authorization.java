package com.kingofnone.rentpentti.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class Authorization {
    private final static String API_URI = "https://api.nordeaopenbanking.com/";
    private final static String ENDPOINT_SUFFIX = "v1/authentication/authorization";
    private final static String CLIENT_ID = "";
    private final static String CLIENT_SECRET = "";
    private final static String REDIRECT_URI = "https://www.httpbin.org/anything";


    public String getToken()  {
        ObjectNode objectNode = getAuthenticationJson();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(objectNode.toString(), new HttpHeaders());
        restTemplate.postForObject(API_URI+ENDPOINT_SUFFIX, objectNode.toString(), String.class);
        return "";
    }

    private ObjectNode getAuthenticationJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("client_id", "");
        objectNode.put("redirect_uri", "");
        objectNode.put("state", "");
        return objectNode;
    }
}
