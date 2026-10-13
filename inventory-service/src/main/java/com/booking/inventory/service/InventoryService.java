package com.booking.inventory.service;

import java.util.UUID;

import com.booking.inventory.dto.CreateInventoryRequest;
import com.booking.inventory.dto.InventoryResponse;
import com.booking.inventory.dto.UpdateInventoryRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryService {

    Mono<InventoryResponse> create(CreateInventoryRequest request);

    Mono<InventoryResponse> update(
            UUID id,
            UpdateInventoryRequest request);

    Mono<InventoryResponse> get(UUID id);

    Flux<InventoryResponse> getBySchedule(UUID scheduleId);

    Flux<InventoryResponse> getChildren(UUID parentInventoryId);

    Mono<Void> delete(UUID id);

}
