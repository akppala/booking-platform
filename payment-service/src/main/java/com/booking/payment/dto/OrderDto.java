package com.booking.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record OrderDto(
        String id,
        String entity,
        Integer amount,
        @JsonProperty("amount_paid") Integer amountPaid,
        @JsonProperty("amount_due") Integer amountDue,
        String currency,
        String receipt,
        String status,
        Integer attempts,
        Map<String, String> notes,
        @JsonProperty("created_at") Long createdAt) {
}
