package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.Pricing;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PricingRepository
        extends ReactiveCrudRepository<Pricing, UUID> {

    Flux<Pricing> findByScheduleId(UUID scheduleId);

    Flux<Pricing> findByInventoryId(UUID inventoryId);

    Mono<Pricing> findByScheduleIdAndInventoryId(
            UUID scheduleId,
            UUID scheduleInventoryId);
}
