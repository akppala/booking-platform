package com.booking.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Map;

public record OrderRequestDto(
        @NotNull @Positive Integer amount,
        @NotBlank String currency,
        @Size(max = 40) String receipt,
        Map<String, String> notes) {
}
