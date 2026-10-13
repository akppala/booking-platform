package com.booking.inventory.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateProviderRequest;
import com.booking.inventory.dto.ProviderResponse;
import com.booking.inventory.dto.UpdateProviderRequest;
import com.booking.inventory.dto.mapper.ProviderMapper;
import com.booking.inventory.entity.Provider;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.ProviderRepository;
import com.booking.inventory.service.ProviderService;
import com.booking.inventory.util.ProviderStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository repository;
    private final ProviderMapper mapper;

    @Override
    public Mono<ProviderResponse> create(CreateProviderRequest request) {

        return repository.existsByCode(request.getCode())

                .flatMap(exists -> {

                    if (exists) {
                        return Mono.error(
                                new BusinessException("Provider code already exists"));
                    }

                    Provider provider = mapper.toEntity(request);
                    provider.setStatus(ProviderStatus.ACTIVE);
                    return repository.save(provider).map(mapper::toResponse);
                });
    }

    @Override
    public Mono<ProviderResponse> get(UUID id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Provider not found")))
                .map(mapper::toResponse);
    }

    @Override
    public Flux<ProviderResponse> getAll() {

        return repository.findAll()
                .map(mapper::toResponse);
    }

    @Override
    public Mono<ProviderResponse> update(UUID id,
                                         UpdateProviderRequest request) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(new NotFoundException("Provider not found")))

                .flatMap(provider -> {

                    provider.setName(request.getName());
                    provider.setDescription(request.getDescription());
                    //provider.setStatus(request.getStatus());

                    return repository.save(provider);

                }).map(mapper::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(new NotFoundException("Provider not found")))

                .flatMap(repository::delete);
    }

}
