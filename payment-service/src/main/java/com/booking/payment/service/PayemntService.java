package com.booking.payment.service;

import com.booking.payment.dto.PaymentDto;
import com.booking.payment.dto.PaymentCaptureRequestDto;

import reactor.core.publisher.Mono;

public interface PayemntService {
    Mono<PaymentDto> processPayment(PaymentCaptureRequestDto request);
}
