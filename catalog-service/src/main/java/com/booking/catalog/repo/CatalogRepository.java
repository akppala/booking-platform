package com.booking.catalog.repo;

import com.booking.catalog.entity.Catalog;
import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CatalogRepository extends BaseRepository<Catalog, UUID>,
                CatalogCustomRepository {

    Mono<Catalog> findByCode(String code);

    Mono<Boolean> existsByCode(String code);

    Flux<Catalog> findByCatalogType(CatalogType catalogType);
    Flux<Catalog> findByCatalogTypeAndStatus(CatalogType catalogType, CatalogStatus status);

    Flux<Catalog> findByStatus(CatalogStatus status);
}