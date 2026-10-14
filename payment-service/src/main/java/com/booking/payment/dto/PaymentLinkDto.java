package com.booking.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record PaymentLinkDto(
        String id,
        String entity,
        Integer amount,
        String currency,
        String status,
        @JsonProperty("accept_partial") Boolean acceptPartial,
        @JsonProperty("amount_paid") Integer amountPaid,
        @JsonProperty("first_min_partial_amount") Integer firstMinPartialAmount,
        String description,
        @JsonProperty("short_url") String shortUrl,
        @JsonProperty("created_at") Long createdAt,
        @JsonProperty("expire_by") Long expireBy,
        @JsonProperty("reference_id") String referenceId,
        PaymentLinkRequestDto.CustomerDto customer,
        Map<String, String> notes,
        @JsonProperty("reminder_enable") Boolean reminderEnable) {
}
