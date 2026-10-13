package com.booking.inventory.dto;

import java.math.BigDecimal;

import com.booking.inventory.util.PricingStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePricingRequest {

    @NotNull
    private BigDecimal basePrice;

    @NotNull
    private BigDecimal tax;

    @NotNull
    private BigDecimal discount;

    @NotNull
    private PricingStatus status;
}
