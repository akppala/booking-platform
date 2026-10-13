package com.booking.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvailabilityTypeRequest {

    @NotNull
    private UUID providerOfferingId;

    private UUID parentTypeId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    private Integer hierarchyLevel;

    @Builder.Default
    private Boolean bookable = Boolean.FALSE;

}
