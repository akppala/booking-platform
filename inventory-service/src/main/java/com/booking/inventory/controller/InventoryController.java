package com.booking.inventory.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.dto.CreateInventoryRequest;
import com.booking.inventory.dto.InventoryResponse;
import com.booking.inventory.dto.UpdateInventoryRequest;
import com.booking.inventory.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public Mono<InventoryResponse> create(
            @Valid @RequestBody CreateInventoryRequest request) {

        return inventoryService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<InventoryResponse> get(
            @PathVariable UUID id) {

        return inventoryService.get(id);
    }

    @PutMapping("/{id}")
    public Mono<InventoryResponse> update(@PathVariable UUID id,
                                          @Valid @RequestBody UpdateInventoryRequest request) {
        return inventoryService.update(id, request);
    }

    @GetMapping("/schedule/{scheduleId}")
    public reactor.core.publisher.Flux<InventoryResponse> getBySchedule(@PathVariable UUID scheduleId) {
        return inventoryService.getBySchedule(scheduleId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id) {
        return inventoryService.delete(id);
    }

}
