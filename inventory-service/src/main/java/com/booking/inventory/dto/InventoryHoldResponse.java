package com.booking.inventory.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.booking.inventory.util.HoldStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryHoldResponse {
    private UUID id;
    private UUID inventoryId;
    private String bookingReference;
    private LocalDateTime expiresAt;
    private HoldStatus status;
}
