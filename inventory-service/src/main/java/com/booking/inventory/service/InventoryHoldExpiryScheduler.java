package com.booking.inventory.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventoryHoldExpiryScheduler {
    private final InventoryHoldService inventoryHoldService;

    @Scheduled(fixedDelayString = "${inventory.holds.expiry-interval-ms:60000}")
    public Mono<Void> expireHolds() {
        return inventoryHoldService.expireOutstandingHolds().then();
    }
}
