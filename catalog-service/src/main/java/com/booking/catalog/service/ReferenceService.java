package com.booking.catalog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import com.booking.catalog.dto.ReferenceRequest;
import com.booking.catalog.dto.ReferenceResponse;

public interface ReferenceService {

    Mono<Void> saveReferences(UUID catalogId,
                              List<ReferenceRequest> references);

    Mono<List<ReferenceResponse>> getReferences(UUID catalogId);

    Mono<Void> deleteReferences(UUID catalogId);

    Mono<ReferenceResponse> create(ReferenceRequest request);

    Mono<ReferenceResponse> findByCode(String code);

    Flux<ReferenceResponse> findByType(String type);

    Mono<Void> delete(String code);
}