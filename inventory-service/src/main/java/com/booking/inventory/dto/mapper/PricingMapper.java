package com.booking.inventory.dto.mapper;

import org.springframework.stereotype.Component;

import com.booking.inventory.dto.CreatePricingRequest;
import com.booking.inventory.dto.PricingResponse;
import com.booking.inventory.entity.Pricing;

@Component
public class PricingMapper {

    public Pricing toEntity(CreatePricingRequest request) {

        return Pricing.builder()
                .scheduleId(request.getScheduleId())
                .inventoryId(request.getScheduleInventoryId())
                .currency(request.getCurrency())
                .basePrice(request.getBasePrice())
                .tax(request.getTax())
                .discount(request.getDiscount())
                .build();
    }

    public PricingResponse toResponse(Pricing entity) {

        return PricingResponse.builder()
                .id(entity.getId())
                .scheduleId(entity.getScheduleId())
                .scheduleInventoryId(entity.getInventoryId())
                .currency(entity.getCurrency())
                .basePrice(entity.getBasePrice())
                .tax(entity.getTax())
                .discount(entity.getDiscount())
                .finalPrice(entity.getFinalPrice())
                .status(entity.getStatus())
                .build();
    }
}
