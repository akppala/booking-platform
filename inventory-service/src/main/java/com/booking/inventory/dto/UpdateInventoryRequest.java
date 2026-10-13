package com.booking.inventory.dto;

import com.booking.inventory.util.InventoryStatus;

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
public class UpdateInventoryRequest {

    private String displayName;

    private InventoryStatus status;
}
