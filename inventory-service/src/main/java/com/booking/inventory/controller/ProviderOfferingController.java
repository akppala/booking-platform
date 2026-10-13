package com.booking.inventory.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.dto.CreateProviderOfferingRequest;
import com.booking.inventory.dto.ProviderOfferingResponse;
import com.booking.inventory.service.ProviderOfferingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/provider-offerings")
public class ProviderOfferingController {

    private final ProviderOfferingService providerOfferingService;

    @PostMapping
    public Mono<ProviderOfferingResponse> create(
            @Valid @RequestBody CreateProviderOfferingRequest request) {

        return providerOfferingService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<ProviderOfferingResponse> get(
            @PathVariable UUID id) {

        return providerOfferingService.get(id);
    }

    @GetMapping
    public Flux<ProviderOfferingResponse> getAll() {

        return providerOfferingService.getAll();
    }

}
