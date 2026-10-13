package com.booking.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateProviderOfferingRequest;
import com.booking.inventory.dto.ProviderOfferingResponse;
import com.booking.inventory.dto.UpdateProviderOfferingRequest;
import com.booking.inventory.dto.mapper.ProviderOfferingMapper;
import com.booking.inventory.entity.ProviderOffering;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.ProviderOfferingRepository;
import com.booking.inventory.repo.ProviderRepository;
import com.booking.inventory.service.ProviderOfferingService;
import com.booking.inventory.util.ProviderOfferingStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProviderOfferingServiceImpl implements ProviderOfferingService {

    private final ProviderOfferingRepository repository;
    private final ProviderRepository providerRepository;
    private final ProviderOfferingMapper mapper;

    @Override
    public Mono<ProviderOfferingResponse> create(CreateProviderOfferingRequest request) {

        return providerRepository.existsById(request.getProviderId())

                .flatMap(providerExists -> {

                    if (!providerExists) {
                        return Mono.error(new NotFoundException("Provider not found"));
                    }

                    return repository.existsByProviderIdAndCatalogId(
                                    request.getProviderId(),
                                    request.getCatalogId())

                            .flatMap(exists -> {

                                if (exists) {
                                    return Mono.error(
                                            new BusinessException(
                                                    "Provider already offers this catalog"));
                                }

                                ProviderOffering entity = mapper.toEntity(request);

                                entity.setStatus(ProviderOfferingStatus.ACTIVE);

                                return repository.save(entity)
                                        .map(mapper::toResponse);
                            });
                });
    }

    @Override
    public Mono<ProviderOfferingResponse> update(UUID id,
                                                 UpdateProviderOfferingRequest request) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Provider Offering not found")))

                .flatMap(entity -> {

                    entity.setName(request.getName());
                    entity.setDescription(request.getDescription());
                    entity.setStatus(request.getStatus());

                    return repository.save(entity);

                }).map(mapper::toResponse);
    }

    @Override
    public Mono<ProviderOfferingResponse> get(UUID id) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Provider Offering not found")))

                .map(mapper::toResponse);
    }

    @Override
    public Flux<ProviderOfferingResponse> getAll() {

        return repository.findAll()
                .map(mapper::toResponse);
    }

    @Override
    public Flux<ProviderOfferingResponse> getByProvider(UUID providerId) {

        return repository.findByProviderId(providerId)
                .map(mapper::toResponse);
    }

    @Override
    public Flux<ProviderOfferingResponse> getByCatalog(UUID catalogId) {

        return repository.findByCatalogId(catalogId)
                .map(mapper::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Provider Offering not found")))

                .flatMap(repository::delete);
    }
}
