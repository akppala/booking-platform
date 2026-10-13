package com.booking.user.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.DefaultServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

        @Bean
        SecurityWebFilterChain securityFilterChain(
                        ServerHttpSecurity http,
                        ServerOAuth2AuthorizationRequestResolver resolver) {

                return http
                                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                                .authorizeExchange(exchanges -> exchanges
                                                .pathMatchers("/actuator/health").permitAll()
                                                .anyExchange().authenticated())

                                // 🔥 THIS IS THE MISSING PART
                                //.oauth2Login(oauth -> oauth
                                                //.authorizationRequestResolver(resolver))

                                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))

                                .build();
        }

        @Bean
        public ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
                        ReactiveClientRegistrationRepository repo) {

                ServerOAuth2AuthorizationRequestResolver defaultResolver = new DefaultServerOAuth2AuthorizationRequestResolver(
                                repo);

                return new ServerOAuth2AuthorizationRequestResolver() {

                        @Override
                        public Mono<OAuth2AuthorizationRequest> resolve(ServerWebExchange exchange) {
                                return defaultResolver.resolve(exchange)
                                                .map(this::modify);
                        }

                        @Override
                        public Mono<OAuth2AuthorizationRequest> resolve(
                                        ServerWebExchange exchange,
                                        String clientRegistrationId) {

                                return defaultResolver.resolve(exchange, clientRegistrationId)
                                                .map(this::modify);
                        }

                        private OAuth2AuthorizationRequest modify(OAuth2AuthorizationRequest req) {

                                Map<String, Object> params = new HashMap<>(req.getAdditionalParameters());

                                params.put("audience", "user-api");

                                return OAuth2AuthorizationRequest.from(req)
                                                .additionalParameters(params)
                                                .build();
                        }
                };
        }
}