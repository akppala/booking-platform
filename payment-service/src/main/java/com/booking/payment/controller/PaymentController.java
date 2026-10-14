package com.booking.payment.controller;

import com.booking.payment.dto.PaymentCaptureRequestDto;
import com.booking.payment.dto.PaymentDto;
import com.booking.payment.service.PayemntService;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PayemntService paymentService;

    public PaymentController(PayemntService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/capture", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PaymentDto> processPayment(@Valid @RequestBody PaymentCaptureRequestDto request) {
        return paymentService.processPayment(request);
    }

}
