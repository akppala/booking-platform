package com.booking.user.service;

import com.booking.user.dto.UserProfile;
import com.booking.user.dto.UserResponse;
import com.booking.user.entity.User;
import com.booking.user.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

        private final UserRepository repository;

        public Mono<UserResponse> currentUser(
                        @RegisteredOAuth2AuthorizedClient("auth0") OAuth2AuthorizedClient client) {

                
                log.info("Fetching current user with Okta User ID: {}", client.getPrincipalName());
                log.info("Fetching current user with Okta User ID: {}", client.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri());

                /*return repository
                                .findByOktaUserId(
                                                oktaUserId)

                                .switchIfEmpty(
                                                createUser(jwt))

                                .map(this::toResponse);*/
                                return Mono.empty();
        }

        public Mono<User> createOrUpdateUser(Jwt jwt) {
                //Jwt jwt = new Jwt(accessToken.getTokenValue(), accessToken.getIssuedAt(), accessToken.getExpiresAt(), null, null);
                log.info("Fetching current user with Okta User ID: {}", jwt.getTokenValue());
                log.info("Fetching current user with Okta User ID: {}", jwt.getSubject());
                return repository.findByOktaUserId(jwt.getSubject()).switchIfEmpty(getUserInfo(jwt.getTokenValue())
                        .flatMap(userProfile -> {
                            log.info("Creating new user with Okta User ID: {}", userProfile.toString());

                            User user = User.builder()
                                    //.id(UUID.randomUUID())
                                    .oktaUserId(userProfile.getSub())
                                    .provider("AUTH0")
                                    .providerUserId(userProfile.getSub())
                                    .email(userProfile.getEmail())
                                    .emailVerified(Boolean.TRUE.equals(
                                            userProfile.getEmailVerified()))
                                    .firstName(userProfile.getGivenName())
                                    .lastName(userProfile.getFamilyName())
                                    .displayName(userProfile.getName())
                                    .profileImageUrl(userProfile.getPicture())
                                    .role("USER")
                                    .status("ACTIVE")
                                    .membershipTier("STANDARD")
                                    .walletBalance(BigDecimal.ZERO)
                                    .createdAt(Instant.now())
                                    .updatedAt(Instant.now())
                                    .lastLoginAt(Instant.now())
                                    .build();

                            return repository.save(user);
                        }))
        .flatMap(existingUser -> {

            existingUser.setLastLoginAt(Instant.now());
            existingUser.setUpdatedAt(Instant.now());

            return repository.save(existingUser);
        });
        }

        private UserResponse toResponse(
                        User user) {

                return new UserResponse(
                                user.getId().toString(),
                                user.getEmail(),
                                user.getDisplayName(),
                                user.getRole(),
                                user.getMembershipTier(),
                                user.getWalletBalance());
        }
         private final WebClient webClient;

    public Mono<UserProfile> getUserInfo(
            String authHeader) {
        log.info("Fetching user info with authHeader: {}", authHeader);
        return webClient
                .get()
                .uri("/userinfo")
                .header("Authorization", "Bearer " + authHeader)
                .retrieve()
                .bodyToMono(UserProfile.class);
    }
    
}