package com.booking.inventory.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateScheduleRequest;
import com.booking.inventory.dto.ScheduleResponse;
import com.booking.inventory.dto.UpdateScheduleRequest;
import com.booking.inventory.entity.Schedule;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.AvailabilityRepository;
import com.booking.inventory.repo.ProviderOfferingRepository;
import com.booking.inventory.repo.ScheduleRepository;
import com.booking.inventory.service.ScheduleService;
import com.booking.inventory.util.ScheduleStatus;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository repository;
    private final ProviderOfferingRepository providerOfferingRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    public Mono<ScheduleResponse> create(CreateScheduleRequest request) {
        if (!request.getEndTime().isAfter(request.getStartTime())) {
            return Mono.error(new BusinessException("Schedule end time must be after its start time"));
        }
        return providerOfferingRepository.existsById(request.getProviderOfferingId())
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new NotFoundException("Provider offering not found")))
                .then(availabilityRepository.findById(request.getOfferingAvailabilityId())
                        .switchIfEmpty(Mono.error(new NotFoundException("Offering availability not found"))))
                .flatMap(availability -> availability.getProviderOfferingId().equals(request.getProviderOfferingId())
                        ? Mono.just(availability)
                        : Mono.error(new BusinessException("Availability belongs to a different provider offering")))
                .then(repository.findByProviderOfferingIdAndScheduleCode(request.getProviderOfferingId(), request.getScheduleCode())
                        .flatMap(existing -> Mono.<Schedule>error(new BusinessException("Schedule code already exists for this provider offering")))
                        .switchIfEmpty(Mono.defer(() -> repository.save(Schedule.builder()
                                .providerOfferingId(request.getProviderOfferingId()).offeringAvailabilityId(request.getOfferingAvailabilityId())
                                .scheduleCode(request.getScheduleCode()).name(request.getName()).description(request.getDescription())
                                .startTime(request.getStartTime()).endTime(request.getEndTime()).capacity(request.getCapacity())
                                .availableCapacity(request.getCapacity()).status(ScheduleStatus.DRAFT).build()))))
                .map(this::toResponse);
    }

    @Override
    public Mono<ScheduleResponse> update(UUID id, UpdateScheduleRequest request) {
        return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Schedule not found")))
                .flatMap(schedule -> {
                    if (request.getName() != null) schedule.setName(request.getName());
                    if (request.getDescription() != null) schedule.setDescription(request.getDescription());
                    if (request.getStartTime() != null) schedule.setStartTime(request.getStartTime());
                    if (request.getEndTime() != null) schedule.setEndTime(request.getEndTime());
                    if (!schedule.getEndTime().isAfter(schedule.getStartTime())) return Mono.error(new BusinessException("Schedule end time must be after its start time"));
                    if (request.getStatus() != null) schedule.setStatus(request.getStatus());
                    return repository.save(schedule);
                }).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ScheduleResponse> get(UUID id) { return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Schedule not found"))).map(this::toResponse); }

    @Override
    @Transactional(readOnly = true)
    public Flux<ScheduleResponse> getByOfferingAvailability(UUID availabilityId) { return repository.findByOfferingAvailabilityId(availabilityId).map(this::toResponse); }

    @Override
    @Transactional(readOnly = true)
    public Flux<ScheduleResponse> getByProviderOffering(UUID providerOfferingId) { return repository.findByProviderOfferingId(providerOfferingId).map(this::toResponse); }

    @Override
    public Mono<Void> delete(UUID id) { return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("Schedule not found"))).flatMap(repository::delete); }

    private ScheduleResponse toResponse(Schedule entity) { return ScheduleResponse.builder().id(entity.getId()).providerOfferingId(entity.getProviderOfferingId()).offeringAvailabilityId(entity.getOfferingAvailabilityId()).scheduleCode(entity.getScheduleCode()).name(entity.getName()).description(entity.getDescription()).startTime(entity.getStartTime()).endTime(entity.getEndTime()).capacity(entity.getCapacity()).availableCapacity(entity.getAvailableCapacity()).status(entity.getStatus()).build(); }
}
