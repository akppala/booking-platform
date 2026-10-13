package com.booking.catalog.repo;

import com.booking.catalog.entity.Reference;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReferenceRepository extends BaseRepository<Reference, UUID>,
                ReferenceCustomRepository {

    Flux<Reference> findByReferenceTypeId(UUID referenceTypeId);

    Mono<Reference> findByCode(String code);

    Mono<Boolean> existsByCode(String code);

}