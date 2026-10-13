package com.booking.inventory.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.booking.inventory.util.PricingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PricingResponse {

    private UUID id;

    private UUID scheduleId;

    private UUID scheduleInventoryId;

    private String currency;

    private BigDecimal basePrice;

    private BigDecimal tax;

    private BigDecimal discount;

    private BigDecimal finalPrice;

    private PricingStatus status;
}
