package com.booking.inventory.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInventoryHoldRequest {
    @NotBlank
    private String bookingReference;

    @NotNull
    @Future
    private LocalDateTime expiresAt;
}
