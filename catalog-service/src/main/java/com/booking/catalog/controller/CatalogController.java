package com.booking.catalog.controller;

import com.booking.common.ApiResponse;
import com.booking.common.ResponseCode;
import com.booking.common.ResponseHandler;
import com.booking.common.search.SearchRequest;
import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.dto.CreateCatalogRequest;
import com.booking.catalog.dto.SearchCatalogRequest;
import com.booking.catalog.dto.UpdateCatalogRequest;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.service.CatalogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
@Slf4j
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/version")
    public Mono<String> version() {
        log.info("Catalog Service version endpoint called");
        return Mono.just("Catalog Service v1");
        //return Mono.error(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Catalog v2 failed"));
    }

    @PostMapping
    public Mono<ApiResponse<CatalogResponse>> create(
            @Valid @NotNull(message = "Request body is required") @RequestBody CreateCatalogRequest request) {
        return catalogService.createCatalog(request).map(response ->
                ResponseHandler.success(
                        response,
                        ResponseCode.CATALOG_CREATED));
    }
    /*@PutMapping("/{id}")
    public Mono<CatalogResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCatalogRequest request) {

        return catalogService.update(id, request);
    }*/

    @GetMapping("/{id}")
    public Mono<Catalog> getById(
            @PathVariable UUID id) {
        log.info("Catalog Service getById endpoint called with id: {}", id);
        return catalogService.findById(id);
    }

    /*@GetMapping("/code/{code}")
    public Mono<CatalogResponse> getByCode(
            @PathVariable String code) {

        return catalogService.findByCode(code);
    }*/

    @PostMapping("/search")
    public Flux<CatalogResponse> search(
            @RequestBody SearchCatalogRequest request) {

        return catalogService.searchCatalog(request);
    }

    /*@DeleteMapping("/{id}")
    public Mono<Void> delete(
            @PathVariable UUID id) {

        return catalogService.delete(id);
    }*/
}