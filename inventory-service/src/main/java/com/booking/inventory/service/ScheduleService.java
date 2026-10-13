package com.booking.inventory.service;

import java.util.UUID;

import com.booking.inventory.dto.CreateScheduleRequest;
import com.booking.inventory.dto.ScheduleResponse;
import com.booking.inventory.dto.UpdateScheduleRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ScheduleService {

    Mono<ScheduleResponse> create(CreateScheduleRequest request);

    Mono<ScheduleResponse> update(
            UUID id,
            UpdateScheduleRequest request);

    Mono<ScheduleResponse> get(UUID id);

    Flux<ScheduleResponse> getByOfferingAvailability(UUID availabilityId);

    Flux<ScheduleResponse> getByProviderOffering(UUID providerOfferingId);

    Mono<Void> delete(UUID id);
}
