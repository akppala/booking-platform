package com.booking.inventory.service;

import java.util.UUID;

import com.booking.inventory.dto.CreateInventoryHoldRequest;
import com.booking.inventory.dto.InventoryHoldResponse;

import reactor.core.publisher.Mono;

public interface InventoryHoldService {
    Mono<InventoryHoldResponse> create(UUID inventoryId, CreateInventoryHoldRequest request);
    Mono<InventoryHoldResponse> confirm(UUID inventoryId, UUID holdId);
    Mono<Void> cancel(UUID inventoryId, UUID holdId);
    Mono<Integer> expireOutstandingHolds();
}
