package com.booking.inventory.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.booking.inventory.dto.AvailabilityTypeResponse;
import com.booking.inventory.dto.CreateAvailabilityTypeRequest;
import com.booking.inventory.entity.UpdateAvailabilityTypeRequest;

public interface AvailabilityTypeService {

    Mono<AvailabilityTypeResponse> create(CreateAvailabilityTypeRequest request);

    Mono<AvailabilityTypeResponse> update(UUID id,
                                          UpdateAvailabilityTypeRequest request);

    Mono<AvailabilityTypeResponse> get(UUID id);

    Flux<AvailabilityTypeResponse> getByProviderOffering(UUID providerOfferingId);

    Mono<Void> delete(UUID id);

}
