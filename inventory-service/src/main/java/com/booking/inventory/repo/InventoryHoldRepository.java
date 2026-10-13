package com.booking.inventory.repo;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.booking.inventory.entity.InventoryHold;
import com.booking.inventory.util.HoldStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryHoldRepository
        extends ReactiveCrudRepository<InventoryHold, UUID> {

    Flux<InventoryHold> findByInventoryId(UUID inventoryId);

    Flux<InventoryHold> findByStatus(HoldStatus status);

    Flux<InventoryHold> findByExpiresAtBefore(LocalDateTime dateTime);

    @Modifying
    @Query("UPDATE inventory_hold SET status = :targetStatus, updated_at = CURRENT_TIMESTAMP, version = version + 1 "
            + "WHERE id = :holdId AND status = :expectedStatus")
    Mono<Integer> transitionStatus(@Param("holdId") UUID holdId,
                                   @Param("expectedStatus") String expectedStatus,
                                   @Param("targetStatus") String targetStatus);

}
