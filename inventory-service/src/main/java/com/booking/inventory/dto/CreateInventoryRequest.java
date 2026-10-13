package com.booking.inventory.dto;

import java.util.UUID;

import com.booking.inventory.util.InventoryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInventoryRequest {

    @NotNull
    private UUID scheduleId;

    private UUID parentInventoryId;

    @NotBlank
    private String inventoryCode;

    @NotBlank
    private String displayName;

    @NotNull
    private InventoryType inventoryType;

    @NotNull
    @Positive
    private Integer capacity;
}
