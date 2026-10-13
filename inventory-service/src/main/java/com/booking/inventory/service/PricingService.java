package com.booking.inventory.service;

import java.util.UUID;

import com.booking.inventory.dto.CreatePricingRequest;
import com.booking.inventory.dto.PricingResponse;
import com.booking.inventory.dto.UpdatePricingRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PricingService {

    Mono<PricingResponse> create(CreatePricingRequest request);

    Mono<PricingResponse> update(
            UUID id,
            UpdatePricingRequest request);

    Mono<PricingResponse> get(UUID id);

    Flux<PricingResponse> getBySchedule(UUID scheduleId);

    Mono<Void> delete(UUID id);
}
