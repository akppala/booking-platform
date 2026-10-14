package com.booking.payment.controller;

import com.booking.payment.dto.PaymentLinkDto;
import com.booking.payment.dto.PaymentLinkRequestDto;
import com.booking.payment.service.PaymentLinkService;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment-links")
public class PaymentLinkController {

    private final PaymentLinkService paymentLinkService;

    public PaymentLinkController(PaymentLinkService paymentLinkService) {
        this.paymentLinkService = paymentLinkService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PaymentLinkDto> createPaymentLink(@Valid @RequestBody PaymentLinkRequestDto request) {
        return paymentLinkService.createPaymentLink(request);
    }
}
