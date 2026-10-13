package com.booking.catalog.service.handler;

import com.booking.catalog.dto.CatalogResponse;
import com.booking.catalog.dto.CreateCatalogRequest;
import com.booking.catalog.dto.CreateMovieRequest;
import com.booking.catalog.dto.MovieResponse;
import com.booking.catalog.dto.SearchCatalogRequest;
import com.booking.catalog.dto.UpdateCatalogRequest;
import com.booking.catalog.entity.Catalog;
import com.booking.catalog.entity.Movie;
import com.booking.catalog.repo.CatalogRepository;
import com.booking.catalog.repo.MovieRepository;
import com.booking.catalog.utils.CatalogStatus;
import com.booking.catalog.utils.CatalogType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieCatalogHandler implements CatalogTypeHandler {

    private final CatalogRepository catalogRepository;
    private final MovieRepository movieRepository;

    @Override
    public CatalogType getCatalogType() {
        return CatalogType.MOVIE;
    }

    @Override
    public Mono<CatalogResponse> create(CreateCatalogRequest request) {

        CreateMovieRequest movieRequest = (CreateMovieRequest) request;

        Catalog catalog = Catalog.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .catalogType(CatalogType.MOVIE)
                .status(CatalogStatus.DRAFT)
                .externalReferenceId(null)
                .build();

        return catalogRepository.save(catalog)
                .flatMap(savedCatalog -> {
                    Movie movie = Movie.builder()
                            .catalogId(savedCatalog.getId())
                            .durationMinutes(movieRequest.getDurationMinutes())
                            .releaseDate(movieRequest.getReleaseDate())
                            .trailerUrl(movieRequest.getTrailerUrl())
                            .build();

                    return movieRepository.save(movie)
                            .map(savedMovie -> MovieResponse.builder()
                                    .id(savedMovie.getId())
                                    .code(savedCatalog.getCode())
                                    .name(savedCatalog.getName())
                                    .description(savedCatalog.getDescription())
                                    .catalogType(savedCatalog.getCatalogType())
                                    .status(savedCatalog.getStatus())
                                    .durationMinutes(savedMovie.getDurationMinutes())
                                    .releaseDate(savedMovie.getReleaseDate())
                                    .trailerUrl(savedMovie.getTrailerUrl())
                                    .build());
                });
    }

    @Override
    public Mono<Void> update(UUID catalogId, UpdateCatalogRequest request) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(UUID catalogId) {
        return movieRepository.deleteById(catalogId);
    }

    @Override
    public Flux<CatalogResponse> search(SearchCatalogRequest request) {
        return catalogRepository.findByCatalogTypeAndStatus(CatalogType.MOVIE, request.getCatalogStatus())
                .flatMap(catalog ->{ 
                    log.info("Catalog found: {}", catalog);
                    return movieRepository.findByCatalogId(catalog.getId())
                        .map(movie -> MovieResponse.builder()
                                .id(catalog.getId())
                                .code(catalog.getCode())
                                .name(catalog.getName())
                                .description(catalog.getDescription())
                                .catalogType(catalog.getCatalogType())
                                .status(catalog.getStatus())
                                .durationMinutes(movie.getDurationMinutes())
                                .releaseDate(movie.getReleaseDate())
                                .trailerUrl(movie.getTrailerUrl())
                                .build());});
    }
}