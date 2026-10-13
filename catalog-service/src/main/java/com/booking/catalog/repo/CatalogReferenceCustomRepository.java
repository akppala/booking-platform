package com.booking.catalog.repo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CatalogReferenceCustomRepository {

    Mono<Void> save(UUID catalogId,
                    String relationshipType,
                    UUID referenceId);

    Mono<Void> delete(UUID catalogId,
                      String relationshipType,
                      UUID referenceId);

    Flux<UUID> findReferenceIds(UUID catalogId);

    Flux<UUID> findReferenceIds(UUID catalogId,
                                String relationshipType);

    Mono<Void> deleteAll(UUID catalogId);

}
