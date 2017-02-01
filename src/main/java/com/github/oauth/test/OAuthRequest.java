package com.github.oauth.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OAuthRequest {
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${requestUrl}")
    private String requestUrl;

    public String request() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setGrantType("password");

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        log.info("Access token: {}", restTemplate.getAccessToken().getValue());
        log.info("Refresh token: {}", restTemplate.getAccessToken().getRefreshToken().getValue());

        ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Request returned status: " + response.getStatusCodeValue());
        }
    }
}
