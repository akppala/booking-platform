package com.booking.inventory.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.booking.inventory.dto.CreateOfferingAvailabilityRequest;
import com.booking.inventory.dto.OfferingAvailabilityResponse;
import com.booking.inventory.dto.OfferingAvailabilitySearchRequest;
import com.booking.inventory.dto.UpdateOfferingAvailabilityRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OfferingAvailabilityService {

    Mono<OfferingAvailabilityResponse> create(
            CreateOfferingAvailabilityRequest request);

    Mono<OfferingAvailabilityResponse> update(
            UUID id,
            UpdateOfferingAvailabilityRequest request);

    Mono<OfferingAvailabilityResponse> get(
            UUID id);

    Flux<OfferingAvailabilityResponse> getAll();

    Flux<OfferingAvailabilityResponse> getByProviderOffering(
            UUID providerOfferingId);

    Flux<OfferingAvailabilityResponse> getByAvailabilityType(
            UUID offeringAvailabilityTypeId);

    Flux<OfferingAvailabilityResponse> getChildren(
            UUID parentAvailabilityId);

    Flux<OfferingAvailabilityResponse> search(
            OfferingAvailabilitySearchRequest request);

    Mono<Void> delete(
            UUID id);

}
