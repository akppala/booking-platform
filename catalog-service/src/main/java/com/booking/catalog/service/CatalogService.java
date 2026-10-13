package com.booking.catalog.service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.dto.CreateCatalogRequest;
import com.booking.catalog.dto.SearchCatalogRequest;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.repo.CatalogRepository;
import com.booking.catalog.service.handler.CatalogHandlerFactory;
import com.booking.catalog.service.handler.CatalogTypeHandler;
import com.booking.catalog.utils.CatalogType;
import com.booking.common.search.QueryBuilder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository repository;
    private final CatalogHandlerFactory handlerFactory;
    private CatalogTypeHandler handler;

    public Mono<CatalogResponse> createCatalog(CreateCatalogRequest request) {
        handler = handlerFactory.getHandler(request.getCatalogType());
        return handler.create(request);
    } 
    public Flux<CatalogResponse> searchCatalog(SearchCatalogRequest request) {
        handler = handlerFactory.getHandler(request.getCatalogType());
        return handler.search(SearchCatalogRequest.builder()
                .keyword(request.getKeyword())
                .catalogType(request.getCatalogType())
                .catalogStatus(request.getCatalogStatus())
                .page(request.getPage())
                .size(request.getSize())
                .build());
    }    

    public Flux<Catalog> getAll() {

        return repository.findAll();
    }

    public Flux<Catalog> byCatalogType(CatalogType type) {

        return repository.findByCatalogType(type);
    }
    public Mono<Catalog> findById(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Catalog not found with id: " + id)));
    }
}
