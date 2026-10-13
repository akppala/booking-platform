/*package com.booking.catalog.service.impl;


import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.dto.CreateCatalogRequest;
import com.booking.catalog.dto.CreateMovieRequest;
import com.booking.catalog.dto.UpdateCatalogRequest;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.repo.CatalogRepository;
import com.booking.catalog.service.CatalogService;
import com.booking.catalog.service.MovieService;
import com.booking.catalog.service.ReferenceService;
import com.booking.common.search.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;
    private final MovieService movieService;
    private final ReferenceService referenceService;
    private final CatalogMapper catalogMapper;
    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<CatalogResponse> create(CreateCatalogRequest request) {

        return validateDuplicateCode(request.getCode())
                .then(saveCatalog(request))
                .flatMap(savedCatalog ->
                        saveMovie(savedCatalog, request)
                                .then(saveReferences(savedCatalog, request))
                                .thenReturn(savedCatalog))
                .map(catalogMapper::toResponse)
                .as(transactionalOperator::transactional);
    }

    /*@Override
    public Mono<CatalogResponse> update(UUID id,
                                        UpdateCatalogRequest request) {

        return catalogRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Catalog not found")))
                .flatMap(existing -> {

                    catalogMapper.updateEntity(existing, request);

                    return catalogRepository.save(existing);
                })
                .map(catalogMapper::toResponse)
                .as(transactionalOperator::transactional);
    }

    @Override
    public Mono<CatalogResponse> findById(UUID id) {

        return catalogRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Catalog not found")))
                .map(catalogMapper::toResponse);
    }

    @Override
    public Mono<CatalogResponse> findByCode(String code) {

        return catalogRepository.findByCode(code)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Catalog not found")))
                .map(catalogMapper::toResponse);
    }

    @Override
    public Flux<CatalogResponse> search(SearchRequest request) {

        return catalogRepository.search(request)
                .map(catalogMapper::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {

        return referenceService.deleteReferences(id)
                .then(movieService.delete(id))
                .then(catalogRepository.deleteById(id))
                .as(transactionalOperator::transactional);
    }

    private Mono<Void> validateDuplicateCode(String code) {

        return catalogRepository.existsByCode(code)
                .flatMap(exists -> {

                    if (exists) {
                        return Mono.error(
                                new RuntimeException(
                                        "Catalog code already exists"));
                    }

                    return Mono.empty();
                });
    }

    private Mono<Catalog> saveCatalog(CreateCatalogRequest request) {

        Catalog catalog = catalogMapper.toEntity(request);

        return catalogRepository.save(catalog);
    }

    private Mono<Void> saveMovie(Catalog catalog,
                                 CreateCatalogRequest request) {

        if (request instanceof CreateMovieRequest movieRequest) {
            return movieService.create(catalog.getId(), movieRequest);
        }

        return Mono.empty();
    }

    private Mono<Void> saveReferences(Catalog catalog,
                                      CreateCatalogRequest request) {

        return referenceService.saveReferences(
                catalog.getId(),
                request.getReferences());
    }
}*/