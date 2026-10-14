package com.booking.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentCaptureRequestDto(
        @NotBlank String paymentId,
        @NotNull @Positive Integer amount,
        @NotBlank String currency) {
}
