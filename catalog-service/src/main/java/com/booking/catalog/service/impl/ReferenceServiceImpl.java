package com.booking.catalog.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.booking.catalog.dto.ReferenceRequest;
import com.booking.catalog.dto.ReferenceResponse;
import com.booking.catalog.service.ReferenceService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReferenceServiceImpl implements ReferenceService {@Override
    public Mono<Void> saveReferences(UUID catalogId, List<ReferenceRequest> references) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveReferences'");
    }

    @Override
    public Mono<List<ReferenceResponse>> getReferences(UUID catalogId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReferences'");
    }

    @Override
    public Mono<Void> deleteReferences(UUID catalogId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReferences'");
    }

    @Override
    public Mono<ReferenceResponse> create(ReferenceRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Mono<ReferenceResponse> findByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCode'");
    }

    @Override
    public Flux<ReferenceResponse> findByType(String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByType'");
    }

    @Override
    public Mono<Void> delete(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}