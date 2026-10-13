package com.booking.inventory.entity;

import com.booking.inventory.service.impl.RecordStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAvailabilityTypeRequest {

    @NotBlank
    private String name;

    @NotNull
    private Boolean bookable;

    @NotNull
    private RecordStatus status;

}
