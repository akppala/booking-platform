package com.booking.inventory.dto;

import java.util.UUID;

import com.booking.inventory.util.InventoryStatus;
import com.booking.inventory.util.InventoryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    private UUID id;

    private UUID scheduleId;

    private UUID parentInventoryId;

    private String inventoryCode;

    private String displayName;

    private InventoryType inventoryType;

    private Integer capacity;

    private Integer booked;

    private InventoryStatus status;
}
