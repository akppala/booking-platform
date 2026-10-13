package com.booking.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient auth0WebClient() {

        return WebClient.builder()
                .baseUrl(
                    "https://dev-g48mdxih18rldnql.us.auth0.com")
                .build();
    }
}
