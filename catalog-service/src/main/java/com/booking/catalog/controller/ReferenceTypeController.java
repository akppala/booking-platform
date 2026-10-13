/*package com.booking.catalog.controller;

import com.booking.catalog.service.ReferenceTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/reference-types")
@RequiredArgsConstructor
public class ReferenceTypeController {

    private final ReferenceTypeService referenceTypeService;

    @PostMapping
    public Mono<Void> create(
            @Valid @RequestBody ReferenceTypeRequest request) {

        return referenceTypeService.create(request);
    }

    @GetMapping
    public Flux<Void> getAll() {

        return referenceTypeService.findAll();
    }

    @DeleteMapping("/{code}")
    public Mono<Void> delete(
            @PathVariable String code) {

        return referenceTypeService.delete(code);
    }
}*/