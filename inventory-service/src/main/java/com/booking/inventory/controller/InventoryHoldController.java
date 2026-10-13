package com.booking.inventory.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.dto.CreateInventoryHoldRequest;
import com.booking.inventory.dto.InventoryHoldResponse;
import com.booking.inventory.service.InventoryHoldService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventories/{inventoryId}/holds")
public class InventoryHoldController {
    private final InventoryHoldService holdService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<InventoryHoldResponse> create(@PathVariable UUID inventoryId, @Valid @RequestBody CreateInventoryHoldRequest request) { return holdService.create(inventoryId, request); }

    @PostMapping("/{holdId}/confirm")
    public Mono<InventoryHoldResponse> confirm(@PathVariable UUID inventoryId, @PathVariable UUID holdId) { return holdService.confirm(inventoryId, holdId); }

    @DeleteMapping("/{holdId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> cancel(@PathVariable UUID inventoryId, @PathVariable UUID holdId) { return holdService.cancel(inventoryId, holdId); }
}
