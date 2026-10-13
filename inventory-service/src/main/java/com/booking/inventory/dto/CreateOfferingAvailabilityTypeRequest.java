package com.booking.inventory.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOfferingAvailabilityTypeRequest {

    @NotNull
    private UUID providerOfferingId;

    private UUID parentTypeId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    private Integer level;

    @NotNull
    private Boolean bookable;

}
