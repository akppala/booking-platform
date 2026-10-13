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

import com.booking.inventory.dto.CreateOfferingAvailabilityRequest;
import com.booking.inventory.dto.OfferingAvailabilityResponse;
import com.booking.inventory.dto.UpdateOfferingAvailabilityRequest;
import com.booking.inventory.service.OfferingAvailabilityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/availabilities")
public class AvailabilityController {

    private final OfferingAvailabilityService availabilityService;

    @PostMapping
    public Mono<OfferingAvailabilityResponse> create(
            @Valid @RequestBody CreateOfferingAvailabilityRequest request) {

        return availabilityService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<OfferingAvailabilityResponse> get(
            @PathVariable UUID id) {

        return availabilityService.get(id);
    }

    @GetMapping
    public reactor.core.publisher.Flux<OfferingAvailabilityResponse> getAll() { return availabilityService.getAll(); }

    @PutMapping("/{id}")
    public Mono<OfferingAvailabilityResponse> update(@PathVariable UUID id, @Valid @RequestBody UpdateOfferingAvailabilityRequest request) { return availabilityService.update(id, request); }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id) { return availabilityService.delete(id); }

}
