package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.ProviderOffering;
import com.booking.inventory.util.ProviderOfferingStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderOfferingRepository extends ReactiveCrudRepository<ProviderOffering, UUID> {

    Flux<ProviderOffering> findByProviderId(UUID providerId);

    Flux<ProviderOffering> findByCatalogId(UUID catalogId);

    Mono<ProviderOffering> findByProviderIdAndCatalogId(UUID providerId, UUID catalogId);

    Mono<Boolean> existsByProviderIdAndCatalogId(UUID providerId, UUID catalogId);

    Flux<ProviderOffering> findByStatus(ProviderOfferingStatus status);

}
