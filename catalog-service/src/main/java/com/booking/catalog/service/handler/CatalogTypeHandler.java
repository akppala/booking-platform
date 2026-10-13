package com.booking.catalog.service.handler;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.dto.CreateCatalogRequest;
import com.booking.catalog.dto.SearchCatalogRequest;
import com.booking.catalog.dto.UpdateCatalogRequest;
import com.booking.catalog.utils.CatalogType;

public interface CatalogTypeHandler {

    CatalogType getCatalogType();

    Mono<CatalogResponse> create(CreateCatalogRequest request);

    Mono<Void> update(UUID catalogId, UpdateCatalogRequest request);

    Mono<Void> delete(UUID catalogId);

    Flux<CatalogResponse> search(SearchCatalogRequest request);
}
