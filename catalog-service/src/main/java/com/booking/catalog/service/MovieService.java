package com.booking.catalog.service;

import reactor.core.publisher.Mono;

import java.util.UUID;

import com.booking.catalog.dto.CreateMovieRequest;

public interface MovieService {

    Mono<Void> create(UUID catalogId,
                      CreateMovieRequest request);

    //Mono<Void> update(UUID catalogId,
                      //UpdateMovieRequest request);

    Mono<Void> delete(UUID catalogId);
}