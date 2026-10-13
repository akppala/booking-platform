package com.booking.catalog.repo;

import com.booking.catalog.entity.ReferenceType;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ReferenceTypeRepository
        extends BaseRepository<ReferenceType, UUID> {

    Mono<ReferenceType> findByCode(String code);

}