package com.booking.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Map;

public record PaymentLinkRequestDto(
        @NotNull @Positive Integer amount,
        @NotBlank String currency,
        @JsonProperty("accept_partial") Boolean acceptPartial,
        @JsonProperty("first_min_partial_amount") @Positive Integer firstMinPartialAmount,
        @JsonProperty("expire_by") @Positive Long expireBy,
        @Size(max = 40) @JsonProperty("reference_id") String referenceId,
        String description,
        @Valid CustomerDto customer,
        @Valid @JsonProperty("notify") NotifyDto notification,
        @JsonProperty("reminder_enable") Boolean reminderEnable,
        Map<String, String> notes,
        @JsonProperty("callback_url") String callbackUrl,
        @JsonProperty("callback_method") String callbackMethod) {

    public record CustomerDto(String name, String email, String contact) {
    }

    public record NotifyDto(Boolean sms, Boolean email) {
    }
}
