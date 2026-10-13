package com.booking.inventory.repo;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.booking.inventory.entity.Schedule;
import com.booking.inventory.util.ScheduleStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ScheduleRepository
        extends ReactiveCrudRepository<Schedule, UUID> {

    Flux<Schedule> findByProviderOfferingId(UUID providerOfferingId);

    Flux<Schedule> findByOfferingAvailabilityId(UUID availabilityId);

    Flux<Schedule> findByStatus(ScheduleStatus status);

    Flux<Schedule> findByStartTimeBetween(
            LocalDateTime from,
            LocalDateTime to);

    Mono<Schedule> findByProviderOfferingIdAndScheduleCode(UUID providerOfferingId, String scheduleCode);

}
