package com.booking.inventory.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.booking.inventory.dto.CreateProviderOfferingRequest;
import com.booking.inventory.dto.ProviderOfferingResponse;
import com.booking.inventory.dto.UpdateProviderOfferingRequest;

public interface ProviderOfferingService {

    Mono<ProviderOfferingResponse> create(CreateProviderOfferingRequest request);

    Mono<ProviderOfferingResponse> update(UUID id,
                                          UpdateProviderOfferingRequest request);

    Mono<ProviderOfferingResponse> get(UUID id);

    Flux<ProviderOfferingResponse> getAll();

    Flux<ProviderOfferingResponse> getByProvider(UUID providerId);

    Flux<ProviderOfferingResponse> getByCatalog(UUID catalogId);

    Mono<Void> delete(UUID id);

}