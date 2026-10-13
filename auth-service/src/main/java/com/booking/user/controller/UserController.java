package com.booking.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.user.entity.User;
import com.booking.user.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private ReactiveOAuth2AuthorizedClientService clientService;

    @GetMapping("/register1")
    public Mono<User> me(Authentication authentication,
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @RequestHeader HttpHeaders headers) {

        log.info("Authentication class: {}", authentication.getClass());
        log.info("Principal class: {}", authentication.getPrincipal().getClass());
        log.info("Principal: {}", authentication.getPrincipal());
        log.info("Name: {}", authentication.getName());
        log.info("Credentials: {}", authentication.getCredentials());
        log.info("Request headers: {}", headers.toSingleValueMap());
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        log.info("Access Token: {}", accessToken);

        return Mono.empty();
    }

    @GetMapping("/info")
    public Mono<Map<String, Object>> userInfo(
            @AuthenticationPrincipal OidcUser user,
            @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient client) {

        System.out.println(client.getClientRegistration().getProviderDetails().getConfigurationMetadata());

        return Mono.just(client.getAccessToken().getTokenValue())
                .map(token -> Map.of(
                        "user", user.getClaims(),
                        "access_token", token));
    }

    @GetMapping("/register")
    public Mono<User> me(JwtAuthenticationToken authentication,
            @RequestHeader HttpHeaders headers) {

        log.info("Request headers: {}", headers.toSingleValueMap());

        return service.createOrUpdateUser(authentication.getToken());
    }
}