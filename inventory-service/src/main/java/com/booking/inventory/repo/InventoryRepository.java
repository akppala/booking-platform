package com.booking.inventory.repo;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;

import com.booking.inventory.entity.Inventory;
import com.booking.inventory.util.InventoryStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryRepository
        extends ReactiveCrudRepository<Inventory, UUID> {

    Flux<Inventory> findByScheduleId(UUID scheduleId);

    Flux<Inventory> findByStatus(InventoryStatus status);

    Mono<Inventory> findByScheduleIdAndInventoryCode(
            UUID scheduleId,
            String inventoryCode);

    Flux<Inventory> findByParentInventoryId(UUID parentInventoryId);

    @Modifying
    @Query("UPDATE inventory SET booked = booked + 1, version = version + 1, updated_at = CURRENT_TIMESTAMP "
            + "WHERE id = :inventoryId AND status = 'AVAILABLE' AND booked < capacity")
    Mono<Integer> reserveOne(@Param("inventoryId") UUID inventoryId);

    @Modifying
    @Query("UPDATE inventory SET booked = booked - 1, version = version + 1, updated_at = CURRENT_TIMESTAMP "
            + "WHERE id = :inventoryId AND booked > 0")
    Mono<Integer> releaseOne(@Param("inventoryId") UUID inventoryId);

}
