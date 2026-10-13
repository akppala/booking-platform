package com.booking.inventory.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booking.inventory.dto.CreateInventoryHoldRequest;
import com.booking.inventory.dto.InventoryHoldResponse;
import com.booking.inventory.entity.InventoryHold;
import com.booking.inventory.exception.BusinessException;
import com.booking.inventory.exception.NotFoundException;
import com.booking.inventory.repo.InventoryHoldRepository;
import com.booking.inventory.repo.InventoryRepository;
import com.booking.inventory.service.InventoryHoldService;
import com.booking.inventory.util.HoldStatus;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryHoldServiceImpl implements InventoryHoldService {
    private final InventoryRepository inventoryRepository;
    private final InventoryHoldRepository holdRepository;

    @Override
    public Mono<InventoryHoldResponse> create(UUID inventoryId, CreateInventoryHoldRequest request) {
        return inventoryRepository.reserveOne(inventoryId)
                .flatMap(rows -> rows == 1
                        ? holdRepository.save(InventoryHold.builder().inventoryId(inventoryId)
                                .bookingReference(request.getBookingReference()).expiresAt(request.getExpiresAt())
                                .status(HoldStatus.ACTIVE).build())
                        : Mono.error(new BusinessException("Inventory is unavailable")))
                .map(this::toResponse);
    }

    @Override
    public Mono<InventoryHoldResponse> confirm(UUID inventoryId, UUID holdId) {
        return holdRepository.findById(holdId).switchIfEmpty(Mono.error(new NotFoundException("Inventory hold not found")))
                .flatMap(hold -> {
                    validateInventory(hold, inventoryId);
                    if (hold.getStatus() != HoldStatus.ACTIVE || !hold.getExpiresAt().isAfter(LocalDateTime.now())) {
                        return Mono.error(new BusinessException("Only an unexpired active hold can be confirmed"));
                    }
                    return holdRepository.transitionStatus(holdId, HoldStatus.ACTIVE.name(), HoldStatus.CONFIRMED.name())
                            .flatMap(rows -> rows == 1 ? holdRepository.findById(holdId) : Mono.error(new BusinessException("Hold state changed; retry the request")));
                }).map(this::toResponse);
    }

    @Override
    public Mono<Void> cancel(UUID inventoryId, UUID holdId) { return release(inventoryId, holdId, HoldStatus.CANCELLED); }

    @Override
    public Mono<Integer> expireOutstandingHolds() {
        return holdRepository.findByStatus(HoldStatus.ACTIVE).filter(hold -> !hold.getExpiresAt().isAfter(LocalDateTime.now()))
                .concatMap(hold -> release(hold.getInventoryId(), hold.getId(), HoldStatus.EXPIRED).thenReturn(1)).reduce(0, Integer::sum);
    }

    private Mono<Void> release(UUID inventoryId, UUID holdId, HoldStatus targetStatus) {
        return holdRepository.findById(holdId).switchIfEmpty(Mono.error(new NotFoundException("Inventory hold not found")))
                .flatMap(hold -> {
                    validateInventory(hold, inventoryId);
                    return holdRepository.transitionStatus(holdId, HoldStatus.ACTIVE.name(), targetStatus.name())
                        .flatMap(rows -> rows == 1 ? inventoryRepository.releaseOne(hold.getInventoryId())
                                .flatMap(released -> released == 1 ? Mono.<Void>empty() : Mono.error(new IllegalStateException("Hold released without reserved inventory")))
                                : Mono.error(new BusinessException("Only an active hold can be released")));
                });
    }

    private void validateInventory(InventoryHold hold, UUID inventoryId) {
        if (!hold.getInventoryId().equals(inventoryId)) throw new NotFoundException("Inventory hold not found");
    }

    private InventoryHoldResponse toResponse(InventoryHold hold) { return InventoryHoldResponse.builder().id(hold.getId()).inventoryId(hold.getInventoryId()).bookingReference(hold.getBookingReference()).expiresAt(hold.getExpiresAt()).status(hold.getStatus()).build(); }
}
