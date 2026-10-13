package com.booking.inventory.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateInventoryRequest;
import com.booking.inventory.dto.InventoryResponse;
import com.booking.inventory.dto.UpdateInventoryRequest;
import com.booking.inventory.entity.Inventory;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.InventoryRepository;
import com.booking.inventory.repo.ScheduleRepository;
import com.booking.inventory.service.InventoryService;
import com.booking.inventory.util.InventoryStatus;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public Mono<InventoryResponse> create(CreateInventoryRequest request) {
        return scheduleRepository.existsById(request.getScheduleId())
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new NotFoundException("Schedule not found")))
                .flatMap(ignored -> validateParent(request))
                .then(repository.findByScheduleIdAndInventoryCode(request.getScheduleId(), request.getInventoryCode())
                        .flatMap(existing -> Mono.<Inventory>error(new BusinessException("Inventory code already exists for this schedule")))
                        .switchIfEmpty(Mono.defer(() -> repository.save(Inventory.builder()
                                .scheduleId(request.getScheduleId())
                                .parentInventoryId(request.getParentInventoryId())
                                .inventoryCode(request.getInventoryCode())
                                .displayName(request.getDisplayName())
                                .inventoryType(request.getInventoryType())
                                .capacity(request.getCapacity())
                                .booked(0)
                                .status(InventoryStatus.AVAILABLE)
                                .build()))))
                .map(this::toResponse);
    }

    private Mono<Void> validateParent(CreateInventoryRequest request) {
        if (request.getParentInventoryId() == null) {
            return Mono.empty();
        }
        return repository.findById(request.getParentInventoryId())
                .switchIfEmpty(Mono.error(new NotFoundException("Parent inventory not found")))
                .flatMap(parent -> parent.getScheduleId().equals(request.getScheduleId())
                        ? Mono.<Void>empty()
                        : Mono.error(new BusinessException("Parent inventory belongs to a different schedule")));
    }

    @Override
    public Mono<InventoryResponse> update(UUID id, UpdateInventoryRequest request) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Inventory not found")))
                .flatMap(entity -> {
                    if (request.getDisplayName() != null) entity.setDisplayName(request.getDisplayName());
                    if (request.getStatus() != null) entity.setStatus(request.getStatus());
                    return repository.save(entity);
                })
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<InventoryResponse> get(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Inventory not found")))
                .map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InventoryResponse> getBySchedule(UUID scheduleId) {
        return repository.findByScheduleId(scheduleId).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InventoryResponse> getChildren(UUID parentInventoryId) {
        return repository.findByParentInventoryId(parentInventoryId).map(this::toResponse);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Inventory not found")))
                .flatMap(inventory -> inventory.getBooked() == 0
                        ? repository.delete(inventory)
                        : Mono.error(new BusinessException("Inventory with active bookings cannot be deleted")));
    }

    private InventoryResponse toResponse(Inventory entity) {
        return InventoryResponse.builder().id(entity.getId()).scheduleId(entity.getScheduleId())
                .parentInventoryId(entity.getParentInventoryId()).inventoryCode(entity.getInventoryCode())
                .displayName(entity.getDisplayName()).inventoryType(entity.getInventoryType())
                .capacity(entity.getCapacity()).booked(entity.getBooked()).status(entity.getStatus()).build();
    }
}
