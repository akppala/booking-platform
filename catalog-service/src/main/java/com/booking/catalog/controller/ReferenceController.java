package com.booking.catalog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.booking.catalog.dto.ReferenceRequest;
import com.booking.catalog.dto.ReferenceResponse;
import com.booking.catalog.service.ReferenceService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/references")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @PostMapping
    public Mono<ReferenceResponse> create(
            @Valid @RequestBody ReferenceRequest request) {

        return referenceService.create(request);
    }

    @GetMapping("/{code}")
    public Mono<ReferenceResponse> getByCode(
            @PathVariable String code) {

        return referenceService.findByCode(code);
    }

    @GetMapping("/type/{type}")
    public Flux<ReferenceResponse> getByType(
            @PathVariable String type) {

        return referenceService.findByType(type);
    }

    @DeleteMapping("/{code}")
    public Mono<Void> delete(
            @PathVariable String code) {

        return referenceService.delete(code);
    }
}