package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.OfferingAvailabilityType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AvailabilityTypeRepository
        extends ReactiveCrudRepository<OfferingAvailabilityType, UUID> {

    Flux<OfferingAvailabilityType> findByProviderOfferingId(UUID providerOfferingId);

    Flux<OfferingAvailabilityType> findByParentTypeId(UUID parentTypeId);

    Mono<OfferingAvailabilityType> findByProviderOfferingIdAndCode(
            UUID providerOfferingId,
            String code);

}
