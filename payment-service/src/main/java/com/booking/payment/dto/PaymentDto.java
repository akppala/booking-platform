package com.booking.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PaymentDto(
        String id,
        String entity,
        Integer amount,
        String currency,
        String status,
        @JsonProperty("order_id") String orderId,
        @JsonProperty("invoice_id") String invoiceId,
        Boolean international,
        String method,
        @JsonProperty("amount_refunded") Integer amountRefunded,
        @JsonProperty("refund_status") String refundStatus,
        Boolean captured,
        String description,
        @JsonProperty("card_id") String cardId,
        String bank,
        String wallet,
        String vpa,
        String email,
        String contact,
        List<Object> notes,
        Integer fee,
        Integer tax,
        @JsonProperty("error_code") String errorCode,
        @JsonProperty("error_description") String errorDescription,
        @JsonProperty("error_source") String errorSource,
        @JsonProperty("error_step") String errorStep,
        @JsonProperty("error_reason") String errorReason,
        @JsonProperty("acquirer_data") AcquirerDataDto acquirerData,
        @JsonProperty("created_at") Long createdAt,
        String reward) {

    public record AcquirerDataDto(
            @JsonProperty("bank_transaction_id") String bankTransactionId) {
    }
}
