package com.booking.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.AvailabilityTypeResponse;
import com.booking.inventory.dto.CreateAvailabilityTypeRequest;
import com.booking.inventory.dto.OfferingAvailabilityTypeResponse;
import com.booking.inventory.dto.mapper.AvailabilityTypeMapper;
import com.booking.inventory.entity.OfferingAvailabilityType;
import com.booking.inventory.entity.UpdateAvailabilityTypeRequest;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.AvailabilityRepository;
import com.booking.inventory.repo.AvailabilityTypeRepository;
import com.booking.inventory.repo.ProviderOfferingRepository;
import com.booking.inventory.service.AvailabilityTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AvailabilityTypeServiceImpl implements AvailabilityTypeService {

    private final AvailabilityTypeRepository repository;
    private final ProviderOfferingRepository providerOfferingRepository;
    private final AvailabilityRepository availabilityRepository;
    private final AvailabilityTypeMapper mapper;

    @Override
    public Mono<AvailabilityTypeResponse> create(CreateAvailabilityTypeRequest request) {

        return providerOfferingRepository.existsById(request.getProviderOfferingId())

                .flatMap(exists -> {

                    if (!exists) {
                        return Mono.error(new NotFoundException("Provider Offering not found"));
                    }

                    return repository.findByProviderOfferingIdAndCode(
                                    request.getProviderOfferingId(),
                                    request.getCode())

                            .flatMap(existing ->
                                    Mono.<OfferingAvailabilityType>error(
                                            new BusinessException("Availability Type already exists")))
                            .switchIfEmpty(Mono.defer(() -> {

                                OfferingAvailabilityType entity = mapper.toEntity(request);
                                entity.setCode(request.getCode());

                                return repository.save(entity);

                            }));

                }).map(mapper::toResponse);
    }

    @Override
    public Mono<AvailabilityTypeResponse> update(UUID id,
                                                 UpdateAvailabilityTypeRequest request) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Availability Type not found")))

                .flatMap(entity -> {

                    entity.setName(request.getName());
                    entity.setBookable(request.getBookable());
                    //entity.setStatus(request.getStatus());

                    return repository.save(entity);

                }).map(mapper::toResponse);
    }

    @Override
    public Mono<AvailabilityTypeResponse> get(UUID id) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Availability Type not found")))

                .map(mapper::toResponse);
    }

    @Override
    public Flux<AvailabilityTypeResponse> getByProviderOffering(UUID providerOfferingId) {

        return repository.findByProviderOfferingId(providerOfferingId)
                .map(mapper::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {

        return repository.findById(id)

                .switchIfEmpty(Mono.error(
                        new NotFoundException("Availability Type not found")))

                .flatMap(type ->

                        repository.findByParentTypeId(type.getId())
                                .hasElements()

                                .flatMap(hasChildren -> {

                                    if (hasChildren) {
                                        return Mono.error(new BusinessException(
                                                "Availability Type has child types"));
                                    }

                                    return availabilityRepository
                                            .findByAvailabilityTypeId(type.getId())
                                            .hasElements();

                                })

                                .flatMap(hasAvailability -> {

                                    if (hasAvailability) {
                                        return Mono.error(new BusinessException(
                                                "Availability Type is already in use"));
                                    }

                                    return repository.delete(type);

                                })
                );
    }
}
