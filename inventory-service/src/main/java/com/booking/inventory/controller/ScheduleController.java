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

import com.booking.inventory.dto.CreateScheduleRequest;
import com.booking.inventory.dto.ScheduleResponse;
import com.booking.inventory.dto.UpdateScheduleRequest;
import com.booking.inventory.service.ScheduleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public Mono<ScheduleResponse> create(
            @Valid @RequestBody CreateScheduleRequest request) {

        return scheduleService.create(request);
    }

    @GetMapping("/{id}")
    public Mono<ScheduleResponse> get(
            @PathVariable UUID id) {

        return scheduleService.get(id);
    }

    @PutMapping("/{id}")
    public Mono<ScheduleResponse> update(@PathVariable UUID id,
                                         @Valid @RequestBody UpdateScheduleRequest request) {
        return scheduleService.update(id, request);
    }

    @GetMapping("/provider-offering/{providerOfferingId}")
    public reactor.core.publisher.Flux<ScheduleResponse> getByProviderOffering(@PathVariable UUID providerOfferingId) {
        return scheduleService.getByProviderOffering(providerOfferingId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable UUID id) {
        return scheduleService.delete(id);
    }

}
