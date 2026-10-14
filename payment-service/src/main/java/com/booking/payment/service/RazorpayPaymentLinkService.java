package com.booking.payment.service;

import com.booking.payment.dto.PaymentLinkDto;
import com.booking.payment.dto.PaymentLinkRequestDto;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RazorpayPaymentLinkService implements PaymentLinkService {

    private final WebClient webClient;
    private final String keyId;
    private final String keySecret;

    public RazorpayPaymentLinkService(
            WebClient.Builder webClientBuilder,
            @Value("${razorpay.base-url:https://api.razorpay.com/v1}") String baseUrl,
            @Value("${razorpay.key-id:}") String keyId,
            @Value("${razorpay.key-secret:}") String keySecret) {
        this.webClient = webClientBuilder.baseUrl(Objects.requireNonNull(baseUrl)).build();
        this.keyId = Objects.requireNonNull(keyId);
        this.keySecret = Objects.requireNonNull(keySecret);
    }

    @Override
    public Mono<PaymentLinkDto> createPaymentLink(PaymentLinkRequestDto request) {
        return Mono.defer(() -> {
            if (keyId.isBlank() || keySecret.isBlank()) {
                return Mono.error(new IllegalStateException(
                        "Razorpay credentials are not configured. Set RAZORPAY_KEY_ID and RAZORPAY_KEY_SECRET."));
            }

            String configuredKeyId = Objects.requireNonNull(keyId);
            String configuredKeySecret = Objects.requireNonNull(keySecret);
            PaymentLinkRequestDto configuredRequest = Objects.requireNonNull(request);
            return webClient.post()
                    .uri("/payment_links")
                    .headers(headers -> headers.setBasicAuth(configuredKeyId, configuredKeySecret))
                    .bodyValue(configuredRequest)
                    .retrieve()
                    .bodyToMono(PaymentLinkDto.class);
        });
    }
}
