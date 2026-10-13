package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.Provider;
import com.booking.inventory.util.ProviderOfferingStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderRepository extends ReactiveCrudRepository<Provider, UUID> {

    Mono<Provider> findByCode(String code);

    Mono<Boolean> existsByCode(String code);

    Flux<Provider> findByStatus(ProviderOfferingStatus status);

}