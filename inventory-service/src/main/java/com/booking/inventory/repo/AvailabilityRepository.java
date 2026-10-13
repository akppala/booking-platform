package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.OfferingAvailability;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AvailabilityRepository
        extends ReactiveCrudRepository<OfferingAvailability, UUID> {

    Flux<OfferingAvailability> findByProviderOfferingId(UUID providerOfferingId);

    Flux<OfferingAvailability> findByParentAvailabilityId(UUID parentAvailabilityId);

    Flux<OfferingAvailability> findByGeoLocationId(UUID geoLocationId);

    Mono<OfferingAvailability> findByProviderOfferingIdAndCode(
            UUID providerOfferingId,
            String code);

    Flux<OfferingAvailability> findByAvailabilityTypeId(UUID id);

}
