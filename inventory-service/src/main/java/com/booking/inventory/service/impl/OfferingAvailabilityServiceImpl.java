package com.booking.inventory.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateOfferingAvailabilityRequest;
import com.booking.inventory.dto.OfferingAvailabilityResponse;
import com.booking.inventory.dto.OfferingAvailabilitySearchRequest;
import com.booking.inventory.dto.UpdateOfferingAvailabilityRequest;
import com.booking.inventory.service.OfferingAvailabilityService;
import com.booking.inventory.entity.OfferingAvailability;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.AvailabilityRepository;
import com.booking.inventory.repo.AvailabilityTypeRepository;
import com.booking.inventory.repo.ProviderOfferingRepository;
import com.booking.inventory.util.Status;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
@Transactional
public class OfferingAvailabilityServiceImpl implements OfferingAvailabilityService {
    private final AvailabilityRepository repository;
    private final ProviderOfferingRepository providerOfferingRepository;
    private final AvailabilityTypeRepository availabilityTypeRepository;

    @Override
    public Mono<OfferingAvailabilityResponse> create(CreateOfferingAvailabilityRequest request) {
        return providerOfferingRepository.existsById(request.getProviderOfferingId()).filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new NotFoundException("Provider offering not found")))
                .then(availabilityTypeRepository.findById(request.getAvailabilityTypeId()).switchIfEmpty(Mono.error(new NotFoundException("Availability type not found"))))
                .flatMap(type -> type.getProviderOfferingId().equals(request.getProviderOfferingId()) ? Mono.just(type) : Mono.error(new BusinessException("Availability type belongs to a different provider offering")))
                .then(repository.findByProviderOfferingIdAndCode(request.getProviderOfferingId(), request.getCode())
                        .flatMap(existing -> Mono.<OfferingAvailability>error(new BusinessException("Availability code already exists for this provider offering")))
                        .switchIfEmpty(Mono.defer(() -> repository.save(OfferingAvailability.builder().providerOfferingId(request.getProviderOfferingId()).availabilityTypeId(request.getAvailabilityTypeId()).parentAvailabilityId(request.getParentAvailabilityId()).geoLocationId(request.getGeoLocationId()).code(request.getCode()).name(request.getName()).description(request.getDescription()).status(Status.ACTIVE).build()))))
                .map(this::toResponse);
    }

    @Override
    public Mono<OfferingAvailabilityResponse> update(UUID id, UpdateOfferingAvailabilityRequest request) {
        return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Offering availability not found"))).flatMap(entity -> { entity.setName(request.getName()); entity.setDescription(request.getDescription()); entity.setStatus(request.getStatus()); return repository.save(entity); }).map(this::toResponse);
    }

    @Override
    public Mono<OfferingAvailabilityResponse> get(UUID id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Offering availability not found"))).map(this::toResponse);
    }

    @Override
    public Flux<OfferingAvailabilityResponse> getAll() {
        return repository.findAll().map(this::toResponse);
    }

    @Override
    public Flux<OfferingAvailabilityResponse> getByProviderOffering(UUID providerOfferingId) {
        return repository.findByProviderOfferingId(providerOfferingId).map(this::toResponse);
    }

    @Override
    public Flux<OfferingAvailabilityResponse> getByAvailabilityType(UUID offeringAvailabilityTypeId) {
        return repository.findByAvailabilityTypeId(offeringAvailabilityTypeId).map(this::toResponse);
    }

    @Override
    public Flux<OfferingAvailabilityResponse> getChildren(UUID parentAvailabilityId) {
        return repository.findByParentAvailabilityId(parentAvailabilityId).map(this::toResponse);
    }

    @Override
    public Flux<OfferingAvailabilityResponse> search(OfferingAvailabilitySearchRequest request) {
        return repository.findAll().filter(entity -> request.getProviderOfferingId() == null || request.getProviderOfferingId().equals(entity.getProviderOfferingId())).filter(entity -> request.getAvailabilityTypeId() == null || request.getAvailabilityTypeId().equals(entity.getAvailabilityTypeId())).filter(entity -> request.getCode() == null || entity.getCode().equalsIgnoreCase(request.getCode())).skip((long) request.getPage() * request.getSize()).take(request.getSize()).map(this::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Offering availability not found"))).flatMap(repository::delete);
    }
    private OfferingAvailabilityResponse toResponse(OfferingAvailability entity) { return OfferingAvailabilityResponse.builder().id(entity.getId()).providerOfferingId(entity.getProviderOfferingId()).availabilityTypeId(entity.getAvailabilityTypeId()).parentAvailabilityId(entity.getParentAvailabilityId()).geoLocationId(entity.getGeoLocationId()).code(entity.getCode()).name(entity.getName()).description(entity.getDescription()).status(entity.getStatus()).build(); }

}
