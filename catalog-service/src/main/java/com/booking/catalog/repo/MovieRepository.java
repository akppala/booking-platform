package com.booking.catalog.repo;

import com.booking.catalog.entity.Movie;

import reactor.core.publisher.Flux;

import java.util.UUID;

public interface MovieRepository extends BaseRepository<Movie, UUID> {

    Flux<Movie> findByCatalogId(UUID catalogId);

}