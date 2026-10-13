package com.booking.inventory.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.inventory.dto.AvailabilityTypeResponse;
import com.booking.inventory.dto.CreateAvailabilityTypeRequest;
import com.booking.inventory.service.AvailabilityTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/availability-types")
public class AvailabilityTypeController {

    private final AvailabilityTypeService availabilityTypeService;

    @PostMapping
    public Mono<AvailabilityTypeResponse> create(
            @Valid @RequestBody CreateAvailabilityTypeRequest request) {

        return availabilityTypeService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<AvailabilityTypeResponse> get(
            @PathVariable UUID id) {

        return availabilityTypeService.get(id);
    }

}

