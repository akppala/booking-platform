package com.booking.inventory.service;

import java.util.UUID;

import com.booking.inventory.dto.CreateProviderRequest;
import com.booking.inventory.dto.ProviderResponse;
import com.booking.inventory.dto.UpdateProviderRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderService {

    Mono<ProviderResponse> create(CreateProviderRequest request);

    Mono<ProviderResponse> update(UUID id, UpdateProviderRequest request);

    Mono<ProviderResponse> get(UUID id);

    Flux<ProviderResponse> getAll();

    Mono<Void> delete(UUID id);

}
