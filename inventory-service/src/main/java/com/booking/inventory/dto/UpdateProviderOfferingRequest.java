package com.booking.inventory.dto;

import com.booking.inventory.util.ProviderOfferingStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProviderOfferingRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private ProviderOfferingStatus status;
}
