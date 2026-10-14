package com.booking.payment.service;

import com.booking.payment.dto.PaymentLinkDto;
import com.booking.payment.dto.PaymentLinkRequestDto;
import reactor.core.publisher.Mono;

public interface PaymentLinkService {
    Mono<PaymentLinkDto> createPaymentLink(PaymentLinkRequestDto request);
}
