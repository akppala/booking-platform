package com.booking.inventory.controller;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.dto.CreateProviderRequest;
import com.booking.inventory.dto.ProviderResponse;
import com.booking.inventory.dto.UpdateProviderRequest;
import com.booking.inventory.service.ProviderService;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/providers")
@Validated
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public Mono<ProviderResponse> create(
            @Valid @RequestBody CreateProviderRequest request) {

        return providerService.create(request);
    }

    @PutMapping("/{id}")
    public Mono<ProviderResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProviderRequest request) {

        return providerService.update(id, request);
    }

    @GetMapping("/{id}")
    public Mono<ProviderResponse> get(
            @PathVariable UUID id) {

        return providerService.get(id);
    }

    @GetMapping
    public Flux<ProviderResponse> getAll() {

        return providerService.getAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(
            @PathVariable UUID id) {

        return providerService.delete(id);
    }

}